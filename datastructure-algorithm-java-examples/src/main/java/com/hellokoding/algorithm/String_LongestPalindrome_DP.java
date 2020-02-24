package com.hellokoding.algorithm;

public class String_LongestPalindrome_DP {
    static String findLongestPalindromicSubstring(String s) {
        if(s == null || s.isEmpty()) return "";

        int n = s.length();
        int startIndex = 0;
        int maxLength = 1;
        boolean[][] P = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            P[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                P[i][i+1] = true;
                startIndex = i;
                maxLength = 2;
            }
        }

        for (int l = 3; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j) && P[i+1][j-1]) {
                    P[i][j] = true;

                    if (l > maxLength) {
                        maxLength = l;
                        startIndex = i;
                    }
                }
            }
        }

        return s.substring(startIndex, startIndex + maxLength);
    }

    public static void main(String[] args) {
        System.out.println(findLongestPalindromicSubstring("a"));
        System.out.println(findLongestPalindromicSubstring("bb"));
        System.out.println(findLongestPalindromicSubstring("ccc"));
        System.out.println(findLongestPalindromicSubstring("bananas"));
    }
}
