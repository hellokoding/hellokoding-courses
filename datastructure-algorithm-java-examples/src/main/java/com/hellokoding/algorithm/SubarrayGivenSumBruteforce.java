package com.hellokoding.algorithm;

import java.util.Arrays;

public class SubarrayGivenSumBruteforce {
    public static int[] findSubarray(int[] A, int sum) {
        for(int i = 0; i < A.length; i++) {

            int currentSum = A[i];

            for(int j = i + 1; j <= A.length; j++) {

                if (currentSum == sum) {
                    return Arrays.copyOfRange(A, i, j);
                } else if (currentSum > sum || j == A.length) {
                    break;
                }

                currentSum += A[j];
            }
        }

        return null;
    }

    public static void main (String[] args) {
        int[] A = {4, 0, 11, 6, 1, 7};
        System.out.println(Arrays.toString(findSubarray(A, 8)));
    }
}
