package com.hellokoding.algorithm;

public class String_LongestPalindrome_BruteForce {
    static String findLongestPalindromicSubstring(String str) {
        String longest = "";
        char[] charArr = str.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            for (int j = i; j < charArr.length; j++) {
                String substr = str.substring(i, j+1);
                if(isPalindrome(substr) && substr.length() > longest.length()) {
                    longest = substr;
                }
            }
        }

        return longest;
    }

    static boolean isPalindrome(String str) {
        String reversedStr = new StringBuilder(str).reverse().toString();
        return str.equals(reversedStr);
    }

    public static void main(String[] args) {
        System.out.println(findLongestPalindromicSubstring("bb"));
    }
}
