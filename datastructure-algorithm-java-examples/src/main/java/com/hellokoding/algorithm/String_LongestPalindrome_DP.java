package com.hellokoding.algorithm;

public class String_LongestPalindrome_DP {
    static String findLongestPalindromicSubstring(String str) {
        int n = str.length();
        int start = 0;
        int maxLength = 1;
        boolean[][] palindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            palindrome[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (str.charAt(i) == str.charAt(i+1)) {
                palindrome[i][i+1] = true;
                start = i;
                maxLength = 2;
            }
        }

        for (int l = 3; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                if (str.charAt(i) == str.charAt(j) && palindrome[i+1][j-1]) {
                    palindrome[i][j] = true;

                    if (l > maxLength) {
                        maxLength = l;
                        start = i;
                    }
                }
            }
        }

        return str.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        System.out.println(findLongestPalindromicSubstring("a"));
        System.out.println(findLongestPalindromicSubstring("bb"));
        System.out.println(findLongestPalindromicSubstring("ccc"));
        System.out.println(findLongestPalindromicSubstring("bananas"));
    }
}
