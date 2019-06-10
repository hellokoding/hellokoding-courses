package com.hellokoding.algorithm;

public class String_LongestCommon {
    static String longestCommonSubstring(String S, String T) {
        int m = S.length();
        int n = T.length();
        int[][] lengths = new int[m+1][n+1];
        int maxLength = 0;
        int endIndex = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j== 0) {
                    lengths[i][j] = 0;
                }
                else if(S.charAt(i-1) == T.charAt(j-1)) {
                    lengths[i][j] = lengths[i-1][j-1] + 1;
                    maxLength = Math.max(maxLength, lengths[i][j]);
                    endIndex = i;
                } else {
                    lengths[i][j] = 0;
                }
            }
        }

        return S.substring(endIndex - maxLength + 1, endIndex + 1);
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubstring("ABABC", "BABCA"));
    }
}
