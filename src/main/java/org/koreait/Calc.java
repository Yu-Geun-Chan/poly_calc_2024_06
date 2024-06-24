package org.koreait;

import java.util.Arrays;
import java.util.Scanner;

public class Calc {
    public static int run(String exp) {

        exp = exp.replaceAll("- ", "+ -");

        String[] bits = exp.split( " \\+ ");

        int a = Integer.parseInt(bits[0]);
        int b = Integer.parseInt(bits[1]);
        int c = 0;

        if (bits.length > 2) {
            c = Integer.parseInt(bits[2]);
        }

        return a + b + c;

    }
}


