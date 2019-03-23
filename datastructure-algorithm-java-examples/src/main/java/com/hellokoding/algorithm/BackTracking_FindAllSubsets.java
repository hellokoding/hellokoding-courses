package com.hellokoding.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BackTracking_FindAllSubsets {
    List<LinkedList<Integer>> result = new ArrayList<>();

    void printSubsets() {
        for (LinkedList<Integer> subset : result) {
            System.out.println(String.valueOf(subset));
        }
    }

    void enumerate(int[] a, int startIndex, LinkedList<Integer> currentSubset) {
        result.add(new LinkedList<>(currentSubset));

        for (int i = startIndex; i < a.length; i++) {
            currentSubset.addLast(a[i]);
            enumerate(a, i + 1, currentSubset);
            currentSubset.removeLast(); // backtracking
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        BackTracking_FindAllSubsets findAllSubsets = new BackTracking_FindAllSubsets();
        findAllSubsets.enumerate(a, 0, new LinkedList<>());
        findAllSubsets.printSubsets();
    }
}
