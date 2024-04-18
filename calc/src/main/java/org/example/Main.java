package org.example;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true){
            try {
                System.out.print("\nВведите математическую операцию: ");
                String input = scan.nextLine().replaceAll("\\s+", "");
                String regex = "^(10|[1-9]\\+|\\-|\\*|\\/)$";
                if(Pattern.matches(regex, input))
                    throw new RuntimeException("Неверный формат операндов или оператора!");
                System.out.println(calc(input));
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(404);
            }
        }
    }

    public static String calc(String input) {
        if (input.isEmpty()) throw new RuntimeException("Введите математическую операцию");
        String[] parts = input.split("[\\+\\-\\*\\/]");
        if (parts.length != 2)
            throw new RuntimeException("Формат математической операции не удовлетворяет " +
                    "заданию - два операнда и один оператор (+, -, /, *)");

        int a = Integer.parseInt(parts[0]);
        if (a > 10 || a < 0) throw new RuntimeException("Число должно быть от 1 до 10");
        int b = Integer.parseInt(parts[1]);
        if (b > 10 || b < 0) throw new RuntimeException("Число должно быть от 1 до 10");

        String action = "";

        for (char c : input.toCharArray()){
            if (c == '+' || c == '-' || c == '*' || c == '/')
                action = String.valueOf(c);
        }
        if (action.isEmpty())
            throw new RuntimeException("Неверная операция");

        int output;

        switch(action) {
            case "+" -> output = a + b;
            case "-" -> output = a - b;
            case "*" -> output = a * b;
            case "/" -> {
                if(b == 0) throw new RuntimeException("Деления на ноль!");
                output = a / b;
            }
            default -> throw new IllegalArgumentException();
        }
        return String.valueOf(output);
    }
}