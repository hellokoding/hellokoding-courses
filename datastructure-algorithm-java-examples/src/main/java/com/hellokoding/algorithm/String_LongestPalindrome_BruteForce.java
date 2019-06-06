package com.hellokoding.algorithm;

public class String_LongestPalindrome_BruteForce {
    static String findLongestPalindromicSubstring(String S) {
        String longestPalindrome = "";
        char[] charArr = S.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            for (int j = i; j < charArr.length; j++) {
                String substr = S.substring(i, j+1);
                if(isPalindrome(substr) && substr.length() > longestPalindrome.length()) {
                    longestPalindrome = substr;
                }
            }
        }

        return longestPalindrome;
    }

    static boolean isPalindrome(String S) {
        String reversedStr = new StringBuilder(S).reverse().toString();
        return S.equals(reversedStr);
    }

    public static void main(String[] args) {
        System.out.println(findLongestPalindromicSubstring("bananas"));
        System.out.println(findLongestPalindromicSubstring("bb"));
        System.out.println(findLongestPalindromicSubstring("a"));
    }
}
