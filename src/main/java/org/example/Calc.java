package org.example;

public class Calc {

    public static int run(String exp) {

        String[] bits = exp.split(" "); // + 기호 사용하려면

        int a = Integer.parseInt(bits[0]);
        String oper = bits[1];
        int b = Integer.parseInt(bits[2]);

        if (oper.equals("+")) {
            return a + b;
        } else {
            return a - b;
        }

    }

}