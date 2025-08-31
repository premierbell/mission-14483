package com.back;

import java.util.Arrays;

public class Calc {
    public static int run(String expression) {
        String[] tokens = expression.trim().split(" ");
        if (tokens.length < 2) {
            return Integer.parseInt(tokens[0]);
        }
        int operatorIdx = findOperatorIdx(tokens);
        int a = Integer.parseInt(tokens[operatorIdx - 1]);
        int b = Integer.parseInt(tokens[operatorIdx + 1]);
        String[] prevTokens = Arrays.copyOfRange(tokens, 0, operatorIdx - 1);
        String[] nextTokens = Arrays.copyOfRange(tokens, operatorIdx + 2, tokens.length);
        String prevExpression = String.join(" ", prevTokens);
        String nextExpression = String.join(" ", nextTokens);
        return switch (tokens[operatorIdx]) {
            case "+" -> run(prevExpression + " " + (a + b) + " " + nextExpression);
            case "-" -> run(prevExpression + " " + (a - b) + " " + nextExpression);
            case "*" -> run(prevExpression + " " + (a * b) + " " + nextExpression);
            case "/" -> run(prevExpression + " " + (a / b) + " " + nextExpression);
            default -> 0;
        };
    }

    private static int findOperatorIdx(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("*") || token.equals("/")) {
                return i;
            }
        }
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("+") || token.equals("-")) {
                return i;
            }
        }
        return -1;
    }
}
