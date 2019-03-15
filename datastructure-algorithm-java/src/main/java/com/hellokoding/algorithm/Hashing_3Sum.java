package com.hellokoding.algorithm;

import java.util.HashSet;
import java.util.Set;

public class Hashing_3Sum {
    boolean contains3(int[] a, int targetSum) {
        for (int i = 0; i < a.length - 2; i++) {
            Set<Integer> hash = new HashSet<>();
            int currentSum = targetSum - a[i];

            for (int j = i+1; j < a.length; j++) {
                if (hash.contains(currentSum - a[j])) {
                    return true;
                }

                hash.add(a[j]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] a = {4, -9, 0, 11, 6, -20, 1, 7};
        System.out.println(new Hashing_3Sum().contains3(a, 10));
    }
}
