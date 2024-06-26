package org.koreait;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {
    public static int run(String exp) {
        exp = exp.trim(); // 양 옆의 쓸데없는 공백 제거 -> " 20 " 을 "20"으로. 단, 가운데 공백은 건드리지 않는다.

        // 괄호 제거
        exp = stripOuterBrackets(exp);

        // 단일항(ex : 10)이 들어오면 바로 리턴
        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        boolean needToMulti = exp.contains(" * ");
        boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
        boolean needToSplit = exp.contains("(") || exp.contains(")");
        boolean needToCompound = needToMulti && needToPlus;

        if (needToSplit) {
            int bracketsCount = 0;
            int splitPointIndex = -1; // 아직 못 찾았어.

            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '(') {
                    bracketsCount++;
                } else if (exp.charAt(i) == ')') {
                    bracketsCount--;
                }
                if (bracketsCount == 0) {
                    splitPointIndex = i;
                    break;
                }
            }

            String firstExp = exp.substring(0, splitPointIndex + 1);
            String secondExp = exp.substring(splitPointIndex + 4);

            char operator = exp.charAt(splitPointIndex + 2);

            exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp);

            return Calc.run(exp);
        } else if (needToCompound) {
            String[] bits = exp.split(" \\+ ");

            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));

            return run(newExp);
        }

        if (needToPlus) {
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
        throw new RuntimeException("해석 불가 : 올바른 계산식이 아니야");
    }

    private static String stripOuterBrackets(String exp) {
        int outerBraketsCount = 0;

        while (exp.charAt(outerBraketsCount) == ('(') && exp.charAt(exp.length() - 1 - outerBraketsCount) == (')')) {
            outerBraketsCount++;
        }
        if (outerBraketsCount == 0) return exp;

        return exp.substring(outerBraketsCount, exp.length() - outerBraketsCount);

    }
}



