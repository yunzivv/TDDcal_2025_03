package org.example;

public class Calc {

    public static int run(String exp) {

        String[] bits = exp.split(" "); // + 기호 사용하려면 이스케이프

        boolean needToPlus = exp.equals("+");
        boolean needToMinus = exp.equals("-");
        boolean needToMultiply = exp.equals("*");
        boolean needToDivide = exp.equals("/");

        if (needToPlus) {
            bits = exp.split(" \\+ ");
        } else if (needToMinus) {
            bits = exp.split(" - ");
        } else if (needToMultiply) {
            bits = exp.split(" * ");
        } else if (needToDivide) {
            bits = exp.split(" / ");
        }


        int a = Integer.parseInt(bits[0]);
        int b = Integer.parseInt(bits[2]);

        if (needToPlus) {
            return a + b;
        } else if (needToMinus) {
            return a - b;
        } else if (needToMultiply) {
            return a * b;
        } else if (needToDivide) {
            return a / b;
        }

        throw new RuntimeException("해석 불가");

    }

}