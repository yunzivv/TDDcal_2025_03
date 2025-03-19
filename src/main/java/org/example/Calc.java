package org.example;

public class Calc {

    public static int run(String exp) { // ((20 + 20)) + 20

        String s = "((20 + 20)) + 20";
        exp = exp.replace("- ", "+ -");
        int sum = 0;

        boolean needToMulti = exp.contains("*");
        boolean needToPlus = exp.contains("+");
        boolean needToCompound = needToPlus && needToMulti;
        boolean needToFirst = exp.contains("(");

        if (needToFirst) {

        }

        if (needToCompound) {
            int num = 0;
            String[] bits = exp.split(" \\+ ");
            for (int i = 0; i < bits.length; i++) {
                if (bits[i].contains("*")) {
                    bits[i] = String.valueOf(run(bits[i]));
                }
                sum += Integer.parseInt(bits[i]);
            }
            return sum;
        }

        if (needToPlus) {

            String[] bits = exp.split(" \\+ ");

            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }

            return sum;
        } else if (needToMulti) {
            String[] bits = exp.split(" \\* ");

            sum = 1;

            for (int i = 0; i < bits.length; i++) {
                sum *= Integer.parseInt(bits[i]);
            }

            return sum;
        }

        throw new RuntimeException("해석 불가 : 올바른 계산식이 아닙니다");
    }

}