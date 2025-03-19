package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    public static int run (String exp){
        // 괄호 제거
        exp = stripOuterBrackets(exp);

        // 단일항이 들어오면 바로 리턴
        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        boolean needToMulti = exp.contains(" * ");
        boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
        boolean needToSplit = exp.contains("(") || exp.contains(")"); // 괄호가 포함 되있는 지
        boolean needToCompound = needToPlus && needToMulti;

        if (needToSplit) {
            int bracketsCount = 0;
            int splitPointIndex = -1; // ')'의 인덱스를 기억하고 그 인덱스를 기준으로 나눈다.

            for (int i = 0; i < exp.length(); i++) { // 문자열을 하나씩 검사
                if (exp.charAt(i) == '(') { // '('를 발견한다면
                    bracketsCount++;  // bracketsCount 1 증가
                } else if (exp.charAt(i) == ')') { // 아니고 ')'를 발견한다면
                    bracketsCount--; // bracketsCount 1 감소
                }
                if (bracketsCount == 0) { // 괄호 쌍이 다 제거가 되었다면
                    splitPointIndex = i; // '('의 인덱스를 기억하고
                    break; // 탈출
                }
            }
            String firstExp = exp.substring(0, splitPointIndex + 1); // '('부터 ')'까지 잘라냄
            String secondExp = exp.substring(splitPointIndex + 4); // 괄호 다음 부호, 공백 뒤로 잘라냄

            return Calc.run(firstExp) + Calc.run(secondExp);

        } else if (needToCompound) {
            String[] bits = exp.split(" \\+ ");

            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));

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

    private static String stripOuterBrackets (String exp){

        int outerBracketsCount = 0;

        while (exp.charAt(outerBracketsCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketsCount) == ')') {
            outerBracketsCount++;
        }

        if (outerBracketsCount == 0) return exp;

        return exp.substring(outerBracketsCount, exp.length() - outerBracketsCount);


    }
}