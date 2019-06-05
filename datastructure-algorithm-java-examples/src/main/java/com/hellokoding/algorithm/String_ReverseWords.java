package com.hellokoding.algorithm;

public class String_ReverseWords {
    static String reverseWords(String str) {
        StringBuilder result = new StringBuilder();
        String[] words = str.split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]).append(" ");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("Hello Koding"));
    }
}
