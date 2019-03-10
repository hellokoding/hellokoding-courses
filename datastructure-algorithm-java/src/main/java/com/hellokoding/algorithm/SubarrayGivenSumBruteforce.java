package com.hellokoding.algorithm;

import java.io.IOException;

public class SubarrayGivenSumBruteforce {
    public static void findSubarray(int[] A, int sum) {
        int N = A.length;

        for(int i = 0; i < N; i++) {

            int currentSum = A[i];

            for(int j = i + 1; j <= N; j++) {

                if (currentSum == sum) {
                    System.out.printf("Subarray found at index %d and %d", i, j);
                    return;
                } else if (currentSum > sum || j == N) {
                    break;
                }

                currentSum += A[j];
            }
        }

        System.out.println(-1);
    }

    public static void main (String[] args) throws IOException{
        int[] A = {4, -9, 0, 11, 6, -20, 1, 7};
        findSubarray(A, 8);
    }
}
