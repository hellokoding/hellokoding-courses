package com.hellokoding.algorithm;

import java.util.Arrays;

public class DP_CoinChange2 {
    static int countMin(int[] coins, int targetCoinChange) {
        int[] minNoOfCoins = new int[targetCoinChange+1];
        Arrays.fill(minNoOfCoins, targetCoinChange + 1);

        minNoOfCoins[0] = 0;

        for (int i = 1; i <= targetCoinChange; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    minNoOfCoins[i] = Math.min(minNoOfCoins[i], minNoOfCoins[i - coins[j]] + 1);
                }
            }
        }

        return minNoOfCoins[targetCoinChange] > targetCoinChange ? -1 : minNoOfCoins[targetCoinChange];
    }

    public static void main(String[] args) {
        System.out.println(countMin(new int[]{2, 3, 1}, 3));
    }
}
