package br.com.alura.challenge.service;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ExchangeRates {

    private String result;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    public String getResult() {
        return result;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }
}
