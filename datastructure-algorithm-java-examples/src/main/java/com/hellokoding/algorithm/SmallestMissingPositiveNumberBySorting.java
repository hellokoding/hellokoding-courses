package com.hellokoding.algorithm;

import java.util.Arrays;

public class SmallestMissingPositiveNumberBySorting {
    public static int findSmallest(int[] a) {
        Arrays.sort(a);
        int min = 1;

        for (int i = 0; i < a.length; i++) {
            if (min == a[i]) {
                min++;
            }
        }

        return min;
    }

    public static void main(String[] args) {
        System.out.println(findSmallest(new int[]{1, 3, 6, 4, 1, 2}));
        System.out.println(findSmallest(new int[]{-1, -3, 5}));
    }
}
