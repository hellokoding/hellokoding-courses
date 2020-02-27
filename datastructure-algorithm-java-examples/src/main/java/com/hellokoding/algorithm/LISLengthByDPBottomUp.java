package com.hellokoding.algorithm;

import java.util.Arrays;

public class LISLengthByDPBottomUp {
    static int lengthOfLIS(int[] a) {
        if (a.length == 0) {
            return 0;
        }

        int[] cache = new int[a.length];
        Arrays.fill(cache, 1);

        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j]) {
                    cache[i] = Math.max(cache[i], cache[j] + 1);
                }
            }
        }

        return Arrays.stream(cache).max().getAsInt();
    }


    public static void main(String[] args) {
        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        System.out.println(lengthOfLIS(arr));
    }
}
