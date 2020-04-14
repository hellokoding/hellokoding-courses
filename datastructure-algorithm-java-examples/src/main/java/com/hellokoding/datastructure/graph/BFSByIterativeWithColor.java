package com.hellokoding.datastructure.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class BFSByIterativeWithColor {
    static final int WHITE = 0, GRAY = 1, BLACK = 2;

    public static void bfsByIterativeWithColor(GraphUndirectedByAdjacencyList g, int v) {
        int[] color = new int[g.getV()];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(v);

        while (!queue.isEmpty()) {
            v = queue.poll();

            if (color[v] == WHITE) {
                color[v] = GRAY;
                System.out.printf("%d ", v);

                for (Integer w : g.getAdjacencyList().get(v)) {
                    queue.offer(w);
                }

                color[v] = BLACK;
            }
        }
    }

    public static void main(String[] args) {
        GraphUndirectedByAdjacencyList g = new GraphUndirectedByAdjacencyList(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(1, 3);
        g.addEdge(2, 4);

        bfsByIterativeWithColor(g, 0);
    }
}
