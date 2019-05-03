package com.hellokoding.algorithm;

public class DP_UniquePaths_Obstacles {
    int countUniquePaths(int[][] A) {
        if (A[0][0] == 1) return 0;

        int rows = A.length;
        int cols = A[0].length;
        int[][] cache = new int[rows][cols];

        cache[0][0] = 1;

        for (int i = 1; i < rows; i++) {
            if (A[i][0] == 0)
                cache[i][0] = cache[i-1][0];
        }

        for (int j = 1; j < cols; j++) {
            if (A[0][j] == 0)
                cache[0][j] = cache[0][j-1];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (A[i][j] == 0)
                    cache[i][j] = cache[i-1][j] + cache[i][j-1];
            }
        }

        return cache[rows-1][cols-1];
    }

    public static void main(String[] args) {
        DP_UniquePaths_Obstacles uniquePaths = new DP_UniquePaths_Obstacles();
        int[][] A = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePaths.countUniquePaths(A));
    }
}
