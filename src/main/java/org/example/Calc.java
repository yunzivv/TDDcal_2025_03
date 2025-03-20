package org.example;

public class Calc {

    public static boolean debug = false; // true로 고쳐 실행 중간의 exp 변화 확인
    public static int runCount = 0;

    public static int run(String exp) {

        // 디버깅 모드
        if(debug) {
            System.out.printf("%d번째 run exp : %s\n", runCount, exp);
        }
        runCount++;

        // 단일항일 경우 0 반환
        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }
        int sum = 0;

        // 어떤 연산을 해야하는 지 미리 확인
        boolean needToMulti = exp.contains("*");
        boolean needToPlus = exp.contains("+") || exp.contains(" - ");
        boolean needToCompound = needToPlus && needToMulti;

        // 괄호를 없앨 때까지 괄호 없애는 함수 실행
        while (exp.contains("(")) {
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
        } else if (needToPlus) {

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

    // 괄호 없애기 함수
    // 괄호 내부를 먼저 연산하고, 그 결과를 기존 위치에 치환한다.
    public static String delpar(String exp) {

        int open = -1; // '(' 위치 저장
        int close = -1; // ')' 위치 저장
        int i; // 괄호의 인덱스 저장

        // 마지막 '('를 찾는다.
        for (i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(') {
                open = i;
            }
        }

        // 마지막 '('의 짝꿍을 찾는다.
        for(i = open; i < exp.length(); i++) {
            if (exp.charAt(i) == ')') {
                close = i;
                break;
            }
        }

        // 가장 먼저 연산해야할 괄호를 기준으로 문자열은 3조각으로 나눠서 배열에 저장
        String[] expparts = new String[3];
        expparts[0] = exp.substring(0, open);
        expparts[1] = exp.substring(open + 1, close);
        expparts[2] = exp.substring(close + 1);

        // 괄호 내부 run
        String parRun = String.valueOf(run(expparts[1]));

        // 괄호 내부를 연산한 결과를 기존 문장과 합체
        String newexp = expparts[0] + parRun + expparts[2];

        return newexp;
    }

}