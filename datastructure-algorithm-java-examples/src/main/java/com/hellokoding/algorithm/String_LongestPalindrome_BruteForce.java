package com.hellokoding.algorithm;

public class String_LongestPalindrome_BruteForce {
    static String findLongestPalindromicSubstring(String str) {
        String longestPalindrome = "";
        char[] charArr = str.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            for (int j = i; j < charArr.length; j++) {
                String substr = str.substring(i, j+1);
                if(isPalindrome(substr) && substr.length() > longestPalindrome.length()) {
                    longestPalindrome = substr;
                }
            }
        }

        return longestPalindrome;
    }

    static boolean isPalindrome(String str) {
        String reversedStr = new StringBuilder(str).reverse().toString();
        return str.equals(reversedStr);
    }

    public static void main(String[] args) {
        System.out.println(findLongestPalindromicSubstring("bb"));
    }
}
