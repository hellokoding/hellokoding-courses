package com.hellokoding.algorithm;

import java.util.ArrayList;
import java.util.List;

public class LISByDPBottomUp {
    static List<Integer> findLIS(int[] arr) {
        List<List<Integer>> cache = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            cache.add(new ArrayList<>());
        }

        cache.get(0).add(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && cache.get(i).size() < cache.get(j).size() + 1) {
                    cache.set(i, new ArrayList<>(cache.get(j)));
                }
            }
            cache.get(i).add(arr[i]);
        }

        List<Integer> longest = cache.get(0);
        for (int i = 0; i < cache.size(); i++) {
            if (longest.size() < cache.get(i).size()) {
                longest = new ArrayList<>(cache.get(i));
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        System.out.println(findLIS(arr).toString());
    }
}
