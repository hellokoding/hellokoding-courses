package com.hellokoding.datastructure.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class DetectCycleDirectedByDFSIterativeColor {
    static final int WHITE = 0, GRAY = 1, BLACK = 2;

    static boolean dfsByIterativeWithColor(GraphDirectedByAdjacencyList g) {
        int[] color = new int[g.getV()];
        Deque<Integer> stack = new ArrayDeque<>(g.getV());

        for (int i = 0; i < g.getV(); i++) {
            if (color[i] != WHITE) continue;
            stack.push(i);

            while (!stack.isEmpty()) {
                int v = stack.peek();

                if (color[v] == WHITE) {
                    color[v] = GRAY;
                } else {
                    color[v] = BLACK;
                    stack.pop();
                }

                for (int w : g.getAdjacencyList().get(v)) {
                    if (color[w] == GRAY) {
                        return true;
                    } else if (color[w] == WHITE) {
                        stack.push(w);
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        GraphDirectedByAdjacencyList g1 = new GraphDirectedByAdjacencyList(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(1, 3);
        g1.addEdge(2, 4);
        System.out.println(dfsByIterativeWithColor(g1));

        GraphDirectedByAdjacencyList g2 = new GraphDirectedByAdjacencyList(4);
        g2.addEdge(0, 1);
        g2.addEdge(2, 0);
        g2.addEdge(2, 1);
        g2.addEdge(3, 2);
        System.out.println(dfsByIterativeWithColor(g2));
    }
}
