package com.hellokoding.algorithm;

public class String_LongestPalindrome_BruteForce {
    static String findLongestPalindromicSubstring(String s) {
        if(s == null || s.isEmpty()) return "";

        String longestPalindrome = "";
        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            for (int j = i; j < charArr.length; j++) {
                String substr = s.substring(i, j+1);
                if(isPalindrome(substr) && substr.length() > longestPalindrome.length()) {
                    longestPalindrome = substr;
                }
            }
        }

        return longestPalindrome;
    }

    static boolean isPalindrome(String s) {
        String reversedStr = new StringBuilder(s).reverse().toString();
        return s.equals(reversedStr);
    }

    public static void main(String[] args) {
        System.out.println(findLongestPalindromicSubstring("bananas"));
        System.out.println(findLongestPalindromicSubstring("bb"));
        System.out.println(findLongestPalindromicSubstring("a"));
    }
}
