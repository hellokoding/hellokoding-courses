package com.hellokoding.algorithm;

public class ReverseWordsByIterative {
    static String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        int j = s.length();

        for (int i = j-1; i >= 0; i--) {
            char c = s.charAt(i);
            if(Character.isWhitespace(c) || i==0) {
                result.append(s, i, j);
                j = i;
            }
        }

        return result.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("Hello  Koding"));
        System.out.println(reverseWords(" Live on time, emit no evil "));
    }
}
