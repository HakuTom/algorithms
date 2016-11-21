package com.company;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder bin = new StringBuilder("1100.1");
        String s = "0.1";

        System.out.println(binaryToHex(s));


    }

    static String binaryToDecimal(String from) {
        double res = 0;
        int index = from.indexOf(".");
        int dot = index == -1 ? from.length() : index;

        for (int i = 0, n = dot - 1; i < dot; i++, n--) {
            res += from.charAt(i) == '1' ? Math.pow(2, n) : 0;
        }

        for (int j = dot + 1, n = -1; j < from.length(); j++, n--) {
            res += from.charAt(j) == '1' ? Math.pow(2, n) : 0;
        }

        return Double.toString(res);
    }

    static String binaryToHex(String from) {
        List<String> num = Arrays.asList("000", "001", "010", "011", "100", "101", "110", "111");
        String[] chars = from.split("\\.");
        String res = "";

        while (chars[0].length() % 3 != 0) {
            chars[0] = 0 + chars[0];
        }

        for (int i = 0; i < chars[0].length() - 2; i += 3) {
            res += num.indexOf(chars[0].substring(i, i + 3));
        }

        if(chars.length > 1) {
            res += '.';
            while (chars[1].length() % 3 != 0) {
                chars[1] = chars[1] + 0;
            }
            for (int i = 0; i < chars[1].length() - 2; i += 3) {
                res += num.indexOf(chars[1].substring(i, i + 3));
            }
        }
        return res;
    }


}


