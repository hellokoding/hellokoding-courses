package com.hellokoding.algorithm;

import java.util.Arrays;

public class DP_CoinChange {
    static int countWays(int[] coins, int targetCoinChange) {
        int[] wayOfCoinChanges = new int[targetCoinChange+1];

        wayOfCoinChanges[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= targetCoinChange; j++) {
                wayOfCoinChanges[j] += wayOfCoinChanges[j - coins[i]];
            }
            System.out.println(Arrays.toString(wayOfCoinChanges));
        }

        return wayOfCoinChanges[targetCoinChange];
    }

    public static void main(String[] args) {
        System.out.println(countWays(new int[]{2, 3, 1}, 4));
    }
}
