package org.example;

public class Calc {

    public static int run(String exp) {

        exp = exp.replace("- ", "+ -");
        String[] bits = null;
        int number;
        int res = 0;

        if (exp.contains("*")) {
            res = 1;
            bits = exp.split(" \\* ");
            for (int i = 0; i < bits.length; i++) {
                number = Integer.parseInt(bits[i]);
                res *=number;
            }
            return res;
        }

        boolean needToPlus = exp.contains("+");

        bits = exp.split(" \\+ ");


        for (int i = 0; i < bits.length; i++) {
            number = Integer.parseInt(bits[i]);
            res +=number;
        }

        return res;
    }

}