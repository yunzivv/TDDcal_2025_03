package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static int run(String exp) { // ((20 + 20)) + 20

        exp = exp.replace("- ", "+ -");
        // 단일항이 들어오면 바로 리턴
        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        boolean needToMulti = exp.contains("*");
        boolean needToPlus = exp.contains("+");

        boolean needToCompound = needToPlus && needToMulti;

        if (needToCompound) {
            String[] bits = exp.split(" \\+ ");

            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));

//            String newExp = "";
//            for (int i = 0; i < bits.length; i++) {
//                int result = Calc.run(bits[i]);
//                newExp += result;
//                if (i < bits.length - 1) {
//                    newExp += " + ";
//                }
//            }


//            StringBuilder newExpBuilder = new StringBuilder();
//            for (int i = 0; i < bits.length; i++) {
//                newExpBuilder.append(Calc.run(bits[i]));
//                if (i < bits.length - 1) {
//                    newExpBuilder.append(" + ");
//                }
//            }
//            String newExp = newExpBuilder.toString();


            return run(newExp);
        }

        if (needToPlus) {
            exp = exp.replace("- ", "+ -");

            String[] bits = exp.split(" \\+ ");

            int sum = 0;

            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }

            return sum;
        } else if (needToMulti) {
            String[] bits = exp.split(" \\* ");

            int sum = 1;

            for (int i = 0; i < bits.length; i++) {
                sum *= Integer.parseInt(bits[i]);
            }

            return sum;
        }

        throw new RuntimeException("해석 불가 : 올바른 계산식이 아닙니다");
    }

}