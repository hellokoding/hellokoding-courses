package com.hellokoding.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SubarrayGivenSumHashtable {
    public static int countSubArrays(int[] a, int k) {
        int cumulativeCount = 0;
        int ci = 0, cj = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int value : a) {
            cj += value;
            ci = cj - k;
            if (map.containsKey(ci)) {
                cumulativeCount += map.get(ci);
            }

            map.put(cj, map.getOrDefault(cj, 0) + 1);
        }

        return cumulativeCount;
    }

    public static void main (String[] args) {
        int[] a = {-4, 12, -11, 6, 1, 7};
        int k = 8;
        System.out.println(countSubArrays(a, k));

        int[] b = {0,0, 0};
        k = 0;
        System.out.println(countSubArrays(b, k));
    }
}
