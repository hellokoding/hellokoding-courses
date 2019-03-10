package com.hellokoding.algorithm;

import java.io.IOException;
import java.util.Arrays;

public class SubarrayGivenSumBruteforce {
    public static int[] findSubarray(int[] A, int sum) {
        int N = A.length;

        for(int i = 0; i < N; i++) {

            int currentSum = A[i];

            for(int j = i + 1; j <= N; j++) {

                if (currentSum == sum) {
                    return Arrays.copyOfRange(A, i, j);
                } else if (currentSum > sum || j == N) {
                    break;
                }

                currentSum += A[j];
            }
        }

        return null;
    }

    public static void main (String[] args) throws IOException{
        int[] A = {4, -9, 0, 11, 6, -20, 1, 7};
        System.out.println(Arrays.toString(findSubarray(A, 8)));
    }
}
