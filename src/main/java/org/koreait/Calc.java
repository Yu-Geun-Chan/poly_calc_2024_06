package org.koreait;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {
    public static int run(String exp) {
        // 괄호 제거
        exp = stripOuterBrakets(exp);

        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        boolean needToDivide = exp.contains(" / ");
        boolean needToMulti = exp.contains(" * ");
        boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
        boolean needToCompound = needToMulti && needToPlus;

        if (needToCompound) {
            exp = exp.replaceAll("- ", "+ -");

            String[] bits = exp.split(" \\+ ");

            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));
            return run(newExp);

        } else if (needToPlus) {
            exp = exp.replaceAll("- ", "+ -");

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
        throw new RuntimeException("올바른 계산식이 아니야");
    }

    private static String stripOuterBrakets(String exp) {
        // index 첫 시작이 여는괄호, index 마지막이 닫는 괄호일 때 변수 exp에다가 괄호 안의 것들을 넣겠다.
        if (exp.charAt(0) == ('(') && exp.charAt(exp.length() - 1) == (')')) {
            exp = exp.substring(1, exp.length() - 1);
        }
        return exp;
    }
}



