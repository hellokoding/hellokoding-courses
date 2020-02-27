package com.hellokoding.algorithm;

public class LISLengthByBruteForce {
    static int lengthOfLIS(int[] a, int prevIndex, int currIndex) {
        if (currIndex == a.length) {
            return 0;
        }

        int included = 0;
        if (prevIndex < 0 || a[currIndex] > a[prevIndex]) {
            included = 1 + lengthOfLIS(a, currIndex, currIndex+1);
        }

        int excluded = lengthOfLIS(a, prevIndex, currIndex+1);

        return Math.max(included, excluded);
    }

    public static void main(String[] args) {
        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        System.out.println(lengthOfLIS(arr, -1, 0));
    }
}
