package com.hellokoding.algorithm;

import java.util.Arrays;

public class TwoPointers_2Sum {
    boolean contains2(int[] a, int targetSum) {
        Arrays.sort(a);

        int i = 0;
        int j = a.length - 1;

        while (i < j) {
            int sum = a[i] + a[j];

            if (sum == targetSum) {
                return true;
            } else if (sum < targetSum) {
                i++;
            } else {
                j--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] a = {4, -9, 0, 11, 6, -20, 1, 7};
        System.out.println(new TwoPointers_2Sum().contains2(a, -14));
    }
}
