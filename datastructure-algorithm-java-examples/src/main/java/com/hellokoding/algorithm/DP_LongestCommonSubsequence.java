package com.hellokoding.algorithm;

public class DP_LongestCommonSubsequence {
    static int lengthOfLongestCommonSubsequence(String S, String T) {
        int m = S.length();
        int n = T.length();
        int[][] lengths = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j== 0) {
                    lengths[i][j] = 0;
                }
                else if(S.charAt(i-1) == T.charAt(j-1)) {
                    lengths[i][j] = lengths[i-1][j-1] + 1;
                } else {
                    lengths[i][j] = Math.max(lengths[i-1][j], lengths[i][j-1]);
                }
            }
        }

        return lengths[m][n];
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestCommonSubsequence("XMJYAUZ", "MZJAWXU"));
    }
}
