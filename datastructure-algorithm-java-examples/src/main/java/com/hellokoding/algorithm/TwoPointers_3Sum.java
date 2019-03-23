package com.hellokoding.algorithm;

import java.util.Arrays;

public class TwoPointers_3Sum {
    boolean contains3(int[] a, int targetSum) {
        Arrays.sort(a);

        for (int i = 0; i < a.length - 2; i++) {
            int j = i + 1;
            int k = a.length - 1;

            while (j < k) {
                int sum = a[i] + a[j] + a[k];

                if (sum == targetSum) {
                    return true;
                } else if (sum < targetSum) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] a = {4, -9, 0, 11, 6, -20, 1, 7};
        System.out.println(new TwoPointers_3Sum().contains3(a, 10));
    }
}
