package org.example;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Locale;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Locale fi = Locale.forLanguageTag("fi-FI");

    public static void main(String[] args) {
        calculateMortgage(loanAmount(), monthlyInterestRate(), monthsForPayment());
    }

    public static int loanAmount() {
        int principal = 0;
        while (true) {

            boolean tryAgain;
            do {
                tryAgain = false;
                try {
                    System.out.print("Principal (€1K - €1M): ");
                    principal = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Try using whole numbers.");
                    tryAgain = true;
                    scanner.nextLine();
                }
            } while (tryAgain);

            if (principal >= 1000 && principal <= 1_000_000)
                return principal;
            else
                System.out.println("Enter a number between 1,000 and 1,000,000");
        }
    }

    public static double monthlyInterestRate() {
        final float MONTHS_IN_YEAR = 12;
        final float PERCENT = 100;

        while (true) {
            boolean tryAgain;
            float annualInterestRate = 0;

            do {
                tryAgain = false;
                try {
                    System.out.print("Annual Interest Rate: ");
                    annualInterestRate = scanner.nextFloat();
                } catch (Exception e) {
                    System.out.println("Type Annual Interest Rate in format \"5,7\" or \"8\".");
                    tryAgain = true;
                    scanner.nextLine();
                }
            } while (tryAgain);

            double monthlyInterestRate;
            if (annualInterestRate > 0 && annualInterestRate <= 30) {
                monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;
                return monthlyInterestRate;
            } else
                System.out.println("Enter a value greater than 0 and less than or equal to 30.");
        }
    }

    public static int monthsForPayment() {
        int period = 0;
        while (true) {
            boolean tryAgain;

            do {
                tryAgain = false;
                try {
                    System.out.print("Period (Years): ");
                    period = scanner.nextByte();
                } catch (Exception e) {
                    System.out.println("Type years in format \"25\".");
                    tryAgain = true;
                    scanner.nextLine();
                }

            } while (tryAgain);

            if (period >= 1 && period <= 30) {
                period *= 12;
                return period;
            } else
                System.out.println("Enter a value between 1 and 30.");
        }
    }

    public static void calculateMortgage(int principal, double monthlyInterestRate, int period) {
        // Calculate mortgage by using mortgage formula
        double mortgage = principal * monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, period) ) /
                (Math.pow(1 + monthlyInterestRate, period) - 1);

        // Convert mortgage to currency
        NumberFormat currency = NumberFormat.getCurrencyInstance(fi);
        String formattedMortgage = currency.format(mortgage);

        // Print the mortgage to terminal
        System.out.println("Mortgage: " + formattedMortgage);
    }
}