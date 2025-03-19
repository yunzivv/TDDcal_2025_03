package org.example;

public class Calc {

    public static int run(String exp) { // 3 * 1 + (1 - (4 * 1 - (1 - 1)))

        // 공백이 없는 문자열(단일항)이라면 정수로 변환해서 반환
        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }
        int sum = 0;

        boolean needToMulti = exp.contains("*");
        boolean needToPlus = exp.contains("+") || exp.contains(" - "); // 음수를 나타내는 게 아닌 빼기 기호일 때만
        boolean needToCompound = needToPlus && needToMulti;
        boolean needToFirst = exp.contains("(");

        if (needToFirst) {
            exp = delpar(exp);
        }

        if (needToCompound) {
            exp = exp.replace("- ", "+ -");
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

            exp = exp.replace("- ", "+ -");
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

        throw new RuntimeException("해석 불가");
    }

    public static String delpar(String exp) {
        int open = exp.indexOf("(");
        int close = exp.lastIndexOf(")");
        if (open == 0) {
            String first = String.valueOf(run(exp.substring(open + 1, close)));
            String after = exp.substring(close + 1);
            exp = first + after;
        } else {
            String first = exp.substring(0, open);
            String after = String.valueOf(run(exp.substring(open + 1, close)));
            exp = first + after;
        }
        return exp;
    }

}