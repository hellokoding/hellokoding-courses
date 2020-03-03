package com.hellokoding.algorithm;

import java.util.HashMap;
import java.util.Map;

public class SubarrayGivenSumHashtable {
    public static int countSubArrays(int[] a, int k) {
        int count = 0;
        int cumulativeSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int value : a) {
            cumulativeSum += value;
            if (map.containsKey(cumulativeSum - k)) {
                count += map.get(cumulativeSum - k);
            }

            map.put(cumulativeSum, map.getOrDefault(cumulativeSum, 0) + 1);
        }

        return count;
    }

    public static void main (String[] args) {
        int[] a = {-4, 12, -11, 6, 1, 7};
        int k = 8;
        System.out.println(countSubArrays(a, k));
    }
}
