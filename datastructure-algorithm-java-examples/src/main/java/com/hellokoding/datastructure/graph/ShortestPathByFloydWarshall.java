package com.hellokoding.datastructure.graph;

import java.util.Arrays;

public class ShortestPathByFloydWarshall {
    static final int I = Integer.MAX_VALUE;

    public static int[][] shortestPathByBFSFloydWarshall(int[][] g) {
        int V = g.length;
        int[][] distances = Arrays.copyOf(g, V);

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (distances[i][k] == I || distances[k][j] == I) continue;
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }

                if (distances[i][i] < 0) {
                    System.out.println("The input graph contains a negative-weight cycle");
                    return new int[][]{};
                }
            }
        }

        return distances;
    }

    static void printResult(int[][] distances) {
        int V = distances.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.printf("%-4s", distances[i][j] == I ? "I" : distances[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] g1 = new int[][] {
            {0, -2, I, I},
            {I, 0, I, I},
            {1, 3, 0, I},
            {I, I, 4, 0}
        };
        printResult(shortestPathByBFSFloydWarshall(g1));

        int[][] g2 = new int[][] {
            {0, -5, I, I},
            {I, 0, 3, I},
            {1, I, 0, I},
            {I, I, 4, 0}
        };
        printResult(shortestPathByBFSFloydWarshall(g2));
    }
}
