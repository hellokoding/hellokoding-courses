package com.hellokoding.algorithm;

import java.util.Arrays;
import java.util.HashSet;

public class SmallestMissingPositiveNumberByHashtable {
    public static int findSmallest(int[] a) {
        HashSet<Integer> hashSet = new HashSet<>();

        for (int value : a) {
            if (value > 0) {
                hashSet.add(value);
            }
        }

        int min = 1;
        while (hashSet.contains(min)) {
            min++;
        }

        return min;
    }

    public static void main(String[] args) {
        System.out.println(findSmallest(new int[]{1, 3, 6, 4, 1, 2}));
        System.out.println(findSmallest(new int[]{-1, -3, 5}));
    }
}
