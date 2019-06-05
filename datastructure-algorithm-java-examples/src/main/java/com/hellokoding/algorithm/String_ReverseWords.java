package com.hellokoding.algorithm;

public class String_ReverseWords {
    static String reverseWords(String str) {
        String result = "";
        String[] words = str.split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            result += words[i] + " ";
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("Hello Koding"));
    }
}
