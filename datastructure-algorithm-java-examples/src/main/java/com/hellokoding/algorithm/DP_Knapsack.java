package com.hellokoding.algorithm;

public class DP_Knapsack {
    int findMaxValueOfKnapSack(int[] values, int[] weights, int W, int N) {
        int[][] cache = new int[N+1][W+1];

        for (int i = 0; i <= N; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    cache[i][w] = 0;
                }
                else if (weights[i-1] > w) {
                    cache[i][w] = cache[i-1][w];
                } else {
                    cache[i][w] = Math.max(cache[i-1][w], cache[i-1][w-weights[i-1]] + values[i-1]);
                }
            }
        }

        return cache[N][W];
    }

    public static void main(String[] args) {
        DP_Knapsack knapsack = new DP_Knapsack();
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int weightLimit = 50;
        int noOfItems = values.length;
        System.out.println(knapsack.findMaxValueOfKnapSack(values, weights, weightLimit, noOfItems));
    }
}
