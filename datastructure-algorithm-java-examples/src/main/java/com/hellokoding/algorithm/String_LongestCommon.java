package com.hellokoding.algorithm;

public class String_LongestCommon {
    static String longestCommon(String S, String T) {
        int m = S.length();
        int n = T.length();
        int[][] lengths = new int[m+1][n+1];
        int z = 0;
        String result = "";

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j== 0) {
                    lengths[i][j] = 0;
                }
                else if(S.charAt(i-1) == T.charAt(j-1)) {
                    lengths[i][j] = lengths[i-1][j-1] + 1;
                    z = Math.max(z, lengths[i][j]);
                    result = S.substring(i-z+1, i+1);
                } else {
                    lengths[i][j] = 0;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestCommon("ABABC", "BABCA"));
    }
}
