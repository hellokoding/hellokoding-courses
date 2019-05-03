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

        /*
        * w[0] = 1
        * i=0, j=2, w[2] = 0 + w[2-2] = 0 + w[0] = 1
        * w[3] = w[3] + w[3-2] = w[3] + w[1] = 0 + 0 = 0
        * w[4] = w[4] + w[4-2] = 0 + w[2] = 1
        *
        * i=1, j=3, w[3] = w[3] + w[3-3] = 1
        * w[4] = w[4] + w[1] = 1
        *
        * i=2, j=1, w[1] = w[1] + w[0] = 1
        * j=2 w[2] = w[2] + w[1] = 2
        * j=3 w[3] = w[3] + w[2] = 3
        * j=4 w[4] = w[4] + w[3] = 4
        *
        * */
    }
}
