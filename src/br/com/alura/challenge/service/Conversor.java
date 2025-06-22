package br.com.alura.challenge.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Map;

public class Conversor {

    private final DecimalFormat df = new DecimalFormat("#0.00");

    public double converter(double valor, String moedaOrigem, String moedaDestino) throws IOException, InterruptedException {
        String url = "https://v6.exchangerate-api.com/v6/8e4a6934e77c5c7c3820252d/latest/" + moedaOrigem;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ExchangeRates rates = deserializarJson(response.body());
        Map<String, Double> conversionRates = rates.getConversionRates();

        if (!conversionRates.containsKey(moedaDestino)) {
            throw new IllegalArgumentException("Moeda de destino inválida: " + moedaDestino);
        }

        double taxa = conversionRates.get(moedaDestino);
        return valor * taxa;
    }

    private ExchangeRates deserializarJson(String json) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        return gson.fromJson(json, ExchangeRates.class);
    }

    public void exibirResultado(double valorOriginal, double valorConvertido, String moedaOrigem, String moedaDestino) {
        String mensagem = String.format(
                "Valor %s [%s] corresponde ao valor final de =>>> %s [%s]",
                df.format(valorOriginal),
                moedaOrigem.toUpperCase(),
                df.format(valorConvertido),
                moedaDestino.toUpperCase()
        );
        System.out.println(mensagem);
    }

    public void converter(double valorOriginal, String moedaOrigem, String moedaDestino, double taxaConversao) {
        double valorConvertido = valorOriginal * taxaConversao;
        exibirResultado(valorOriginal, valorConvertido, moedaOrigem, moedaDestino);
    }
}