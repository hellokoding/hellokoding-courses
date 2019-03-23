package com.hellokoding.algorithm;

import java.util.Arrays;

public class MergeSortTopDown {
    public int[] sort(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return arr;
        }

        // divide the array in half
        int[] left = Arrays.copyOfRange(arr, 0, n/2);
        int[] right = Arrays.copyOfRange(arr, n/2, n);

        // sort/conquer each half
        int[] sortedLeft = sort(left);
        int[] sortedRight = sort(right);

        // merge/combine the sorted halves
        return merge(sortedLeft, sortedRight, n);
    }

    private int[] merge(int[] left, int[] right, int n) {
        int[] result = new int[n];
        int l = 0;
        int r = 0;

        for (int i = 0; i < n; i++) {
            if (l < left.length && (r >= right.length || left[l] < right[r])) {
                result[i] = left[l];
                l++;
            } else {
                result[i] = right[r];
                r++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] a = {7, 9, 2, 3, 1};
        a = new MergeSortTopDown().sort(a);

        Arrays.stream(a).forEach(System.out::println);
    }
}
