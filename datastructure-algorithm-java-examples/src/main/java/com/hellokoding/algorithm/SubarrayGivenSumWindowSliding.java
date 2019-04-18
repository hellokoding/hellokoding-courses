package com.hellokoding.algorithm;

import java.util.Arrays;

public class SubarrayGivenSumWindowSliding {
    public static int[] findSubarray(int[] A, int sum) {
        int windowSum = 0, i = 0, j = 0;

        while (i < A.length) {
            while (j < A.length && windowSum < sum) {
                windowSum += A[j++];
            }

            if (windowSum == sum) {
                return Arrays.copyOfRange(A, i, j);
            }

            windowSum -= A[i++];
        }

        return null;
    }

    public static void main (String[] args) {
        int[] A = {4, 0, 11, 6, 1, 7};
        System.out.println(Arrays.toString(findSubarray(A, 8)));
    }
}
