package com.hellokoding.algorithm;

public class SubarrayMaxSumBruteforce {
    public static int maxSumBruteforce(int[] A, int k) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < A.length - k + 1; i++) {
            int currentSum = 0;

            for (int j = 0; j < k; j++) {
                currentSum += A[i+j];
            }

            if (currentSum > maxSum) maxSum = currentSum;
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSumBruteforce(new int[]{5, 7, 2, 9, 4}, 2));
    }
}
