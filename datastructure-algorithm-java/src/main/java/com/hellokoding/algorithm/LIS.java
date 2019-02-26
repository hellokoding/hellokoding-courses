package com.hellokoding.algorithm;

import java.util.ArrayList;
import java.util.List;

public class LIS {
    static List<Integer> findLIS(int[] arr) {
        List<List<Integer>> lis = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            lis.add(new ArrayList<>());
        }

        lis.get(0).add(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis.get(i).size() < lis.get(j).size() + 1) {
                    lis.set(i, new ArrayList<>(lis.get(j)));
                }
            }
            lis.get(i).add(arr[i]);
        }

        List<Integer> longest = lis.get(0);
        for (int i = 0; i < lis.size(); i++) {
            if (longest.size() < lis.get(i).size()) {
                longest = new ArrayList<>(lis.get(i));
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        int arr[] = {3, 2, 6, 4, 5, 1};
        System.out.println("LIS: " + findLIS(arr).toString());
    }
}
