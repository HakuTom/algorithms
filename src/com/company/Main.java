package com.company;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String s = "3B.8";
        double d = 5.5;
//        System.out.println(decimalToBin(s, 6));
        String a = "A.5";
        String b = "1001.11";
        System.out.println(hexTobin(a));
        //System.out.println((a.charAt(0)  + a.charAt(0) - '0' - '0'));

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

    static String binaryToOct(String from) {
        List<String> num = Arrays.asList("000", "001", "010", "011", "100", "101", "110", "111");
        String[] chars = from.split("\\.");
        String res = "";

        while (chars[0].length() % 3 != 0) {
            chars[0] = 0 + chars[0];
        }

        for (int i = 0; i < chars[0].length() - 2; i += 3) {
            res += num.indexOf(chars[0].substring(i, i + 3));
        }

        if (chars.length > 1) {
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

    static String binaryToHex(String from) {
        List<String> num = Arrays.asList(
                "0000", "0001", "0010",
                "0011", "0100", "0101",
                "0110", "0111", "1000",
                "1001", "1010", "1011",
                "1100", "1101", "1110",
                "1111"
        );
        String[] hexNums = {"A", "B", "C", "D", "E", "F"};

        String[] chars = from.split("\\.");
        String res = "";
        int n;

        while (chars[0].length() % 4 != 0) {
            chars[0] = 0 + chars[0];
        }

        for (int i = 0; i < chars[0].length() - 3; i += 4) {
            n = num.indexOf(chars[0].substring(i, i + 4));
            res += n < 10 ? n : hexNums[n - 10];
        }

        if (chars.length > 1) {
            res += '.';
            while (chars[1].length() % 4 != 0) {
                chars[1] = chars[1] + 0;
            }
            for (int i = 0; i < chars[1].length() - 2; i += 4) {
                n = num.indexOf(chars[1].substring(i, i + 4));
                res += n < 10 ? n : hexNums[n - 10];
            }
        }
        return res;
    }

    static String decimalToBin(String from, int precision) {
        double number = Double.parseDouble(from);
        int intPart = (int) number;
        double fractPart = number - intPart;

        String result = "";

        while (intPart >= 1) {
            int temp = intPart % 2;
            result = temp + result;
            intPart /= 2;
        }
        result += result.length() == 0 ? "0." : '.';
        for (int i = 0; i < precision; i++) {
            double temp = fractPart * 2;
            result += (int) temp;
            fractPart = temp - (int) temp;
        }
        return result;
    }

    static String octalTobin(String from) {
        List<String> num = Arrays.asList("000", "001", "010", "011", "100", "101", "110", "111");

        String result = "";

        for (int i = 0; i < from.length(); i++) {
            char temp = from.charAt(i);
            if (temp != '.') {
                result += num.get(temp - '0');
            } else
                result += '.';
        }
        return result;
    }

    static String hexTobin(String from) {
        List<String> num = Arrays.asList(
                "0000", "0001", "0010",
                "0011", "0100", "0101",
                "0110", "0111", "1000",
                "1001", "1010", "1011",
                "1100", "1101", "1110",
                "1111"
        );
        List<Character> hexNum = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F');

        String result = "";

        for (int i = 0; i < from.length(); i++) {
            char temp = from.charAt(i);
            if (temp != '.') {
                int index = hexNum.indexOf(temp);
                result += index == -1 ? num.get(temp - '0') : num.get(index + 10);
            } else
                result += '.';
        }
        return result;
    }

    //TODO: adjust length of stings
    static String addBin(String a, String b) {
        String c = "";
        int i = a.length() - 1;
        int j = b.length() - 1;
        int remainder = 0;

        while (i >= 0 && j >= 0) {
            char x = a.charAt(i);
            char y = b.charAt(j);

            remainder = x + y - '0' - '0';
            if (remainder > 1){
                c = '0' + c;
                remainder--;
            } else {
                c = '1' + c;
                remainder = 0;
            }
            i--;
            j--;
        }
        if (remainder == 1)
            c = '1' + c;
        return c;
    }
}


