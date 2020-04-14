package com.hellokoding.datastructure.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class DFSByIterativeWithColor {
    static final int WHITE = 0, GRAY = 1, BLACK = 2;

    static void dfsByIterativeWithColor(GraphUndirectedByAdjacencyList g, int v) {
        int[] color = new int[g.getV()];

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(v);

        while (!stack.isEmpty()) {
            v = stack.pop();

            if (color[v] == WHITE) {
                color[v] = GRAY;
                System.out.printf("%d ", v);

                for(Integer w : g.getAdjacencyList().get(v)) {
                    stack.push(w);
                }

                color[v] = BLACK;
            }
        }
    }

    public static void main(String[] args) {
        GraphUndirectedByAdjacencyList g = new GraphUndirectedByAdjacencyList(5);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 0);
        g.addEdge(1, 3);
        g.addEdge(2, 4);

        dfsByIterativeWithColor(g, 0);
    }
}
