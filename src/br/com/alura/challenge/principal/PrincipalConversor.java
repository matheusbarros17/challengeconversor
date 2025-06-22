package br.com.alura.challenge.principal;

import br.com.alura.challenge.service.Conversor;

import java.util.Locale;
import java.util.Scanner;

public class PrincipalConversor {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        Conversor conversor = new Conversor();

        while (true) {
            System.out.println("\nSeja bem-vindo/a ao Conversor de Moedas");
            System.out.println("""
                    \n1) Dólar => Peso argentino
                    2) Peso argentino => Dólar
                    3) Dólar => Real brasileiro
                    4) Real brasileiro => Dólar
                    5) Dólar => Peso colombiano
                    6) Peso colombiano => Dólar
                    7) Sair
                    Escolha uma opção válida:
                    *********************************************************
                    """);

            int opcao = scanner.nextInt();

            if (opcao == 7) {
                System.out.println("Encerrando o programa...");
                break;
            }

            System.out.println("Digite o valor que deseja converter: ");
            double valor = scanner.nextDouble();

            String moedaOrigem = "USD";
            String moedaDestino = "";

            switch (opcao) {
                case 1 -> moedaDestino = "ARS";
                case 2 -> {
                    moedaOrigem = "ARS";
                    moedaDestino = "USD";
                }
                case 3 -> moedaDestino = "BRL";
                case 4 -> {
                    moedaOrigem = "BRL";
                    moedaDestino = "USD";
                }
                case 5 -> moedaDestino = "COP";
                case 6 -> {
                    moedaOrigem = "COP";
                    moedaDestino = "USD";
                }
                default -> {
                    System.out.println("Opção inválida!");
                    continue;
                }
            }

            try {
                double convertido = conversor.converter(valor, moedaOrigem, moedaDestino);
                System.out.printf("Valor %.2f [%s] corresponde ao valor final de =>>> %.2f [%s]%n",
                        valor, moedaOrigem, convertido, moedaDestino);
            } catch (Exception e) {
                System.out.println("Erro ao converter: " + e.getMessage());
            }
        }

        scanner.close();
    }
}