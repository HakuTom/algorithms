package com.company;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        long lBegin = System.currentTimeMillis();

        Scanner scanner = new Scanner(new FileInputStream("/home/dokgo/Documents/inStream.txt"));
        int amount = scanner.nextInt();
        Language[] langs = new Language[amount];
        scanner.nextLine();
        int index;
        String line;

        for (int i = 0; i < amount; i++) {
            line = scanner.nextLine();
            index = line.indexOf(" ");
            if (index != -1)
                langs[i] = new Language(line.substring(0, index), line.substring(index + 1));
        }

        final String lastLine = scanner.nextLine();
        final int laxity = 10 * lastLine.length() / 100;

        if(laxity > 260){
            System.out.println("NO SOLUTION");
            return;
        }

        Set<Character> scroll = new HashSet<>();
        for (int i = 0; i < lastLine.length(); i++) {
            scroll.add(lastLine.charAt(i));
        }

        Set<Character> temp;
        boolean flag = true;
        for (Language lang : langs) {
            temp = new HashSet<>(scroll);

            if (temp.retainAll(lang.alphabet) && temp.size() > laxity) {
                System.out.println(lang.name);
                flag = false;
            }
        }

        if (flag)
            System.out.println("NO SOLUTION");


        long lEnd = System.currentTimeMillis();
        long lDelta = lEnd - lBegin;

        System.out.printf("Amount: %d\nScroll: %s\nLaxity: %s\n", amount, scroll, laxity);
        //System.out.println(Arrays.toString(langs));
        System.out.printf("Test time: %d ms", lDelta);
    }

}

class Language {
    String name;
    Set<Character> alphabet;

    public Language(String name, String alph) {
        this.name = name;
        this.alphabet = new HashSet<>();
        for (int i = 0; i < alph.length(); i++) {
            this.alphabet.add(alph.charAt(i));
        }

    }

    @Override
    public String toString() {
        return ("Name: " + name + " Alph: " + alphabet);
    }
}
