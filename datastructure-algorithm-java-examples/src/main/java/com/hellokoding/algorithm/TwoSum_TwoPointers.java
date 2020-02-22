package com.hellokoding.algorithm;

import java.util.Arrays;

public class TwoSum_TwoPointers {
    static boolean twoPointers(int[] a, int k) {
        Arrays.sort(a);

        int i = 0;
        int j = a.length - 1;

        while (i < j) {
            int sum = a[i] + a[j];

            if (sum == k) {
                return true;
            } else if (sum < k) {
                i++;
            } else {
                j--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 7};
        System.out.println(twoPointers(a, 8));
        System.out.println(twoPointers(a, 5));

        int[] b = {4, -9, 0, 11, 6, -20, 1, 7};
        System.out.println(twoPointers(b, -14));
        System.out.println(twoPointers(b, -15));
    }
}
