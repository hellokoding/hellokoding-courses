package com.hellokoding.algorithm;

public class DP_UniquePaths {
    int countUniquePaths(int rows, int cols) {
        int[][] cache = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            cache[i][0] = 1;
        }

        for (int j = 0; j < cols; j++) {
            cache[0][j] = 1;
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                cache[i][j] = cache[i-1][j] + cache[i][j-1];
            }
        }

        return cache[rows-1][cols-1];
    }

    public static void main(String[] args) {
        DP_UniquePaths uniquePaths = new DP_UniquePaths();
        System.out.println(uniquePaths.countUniquePaths(3, 3));
    }
}
