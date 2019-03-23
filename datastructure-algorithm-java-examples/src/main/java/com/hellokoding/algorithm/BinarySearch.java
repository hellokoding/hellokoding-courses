package com.hellokoding.algorithm;

public class BinarySearch {
    public static int binarySearch(int T, int[] A) {
        int N = A.length;
        int L = 0;
        int R = N - 1;

        while (L <= R) {
            int M = (int)Math.floor((L+R)/2);
            if (A[M] < T)
                L = M + 1;
            else if (A[M] > T)
                R = M - 1;
            else
                return M;
        }

        return -1;
    }
}
