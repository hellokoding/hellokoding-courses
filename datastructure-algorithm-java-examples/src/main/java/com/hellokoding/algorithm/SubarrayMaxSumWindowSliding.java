package com.hellokoding.algorithm;

public class SubarrayMaxSumWindowSliding {
    public static int maxSumWindowSliding(int[] A, int k) {
        int windowsSum = 0, maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < k; i++) {
            windowsSum += A[i];
        }

        for (int i = k; i < A.length; i++) {
            windowsSum += A[i] - A[i-k];
            if (windowsSum > maxSum) maxSum = windowsSum;
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSumWindowSliding(new int[]{-5, -7, -2, -9, -4}, 2));
    }
}
