package com.hellokoding.datastructure.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class DetectCycleDirectedByDFSIterativeColor {
    static final int WHITE = 0, GRAY = 1, BLACK = 2;

    static boolean dfsByIterativeWithColor(GraphDirectedByAdjacencyList g, int v, int[] color) {
        color[v] = GRAY;

        Deque<Integer> stack = new ArrayDeque<>(g.V);
        stack.push(v);

        while (!stack.isEmpty()){
            v = stack.peek();

            List<Integer> adjacencies = g.adjacencyList.get(v);
            if (!adjacencies.isEmpty()) {
                for (int w : adjacencies) {
                    if (color[w] == GRAY) {
                        return true;
                    } else if (color[w] == WHITE) {
                        color[w] = GRAY;
                        stack.push(w);
                    }
                }
            } else {
                color[v] = BLACK;
                stack.pop();
            }
        }

        return false;
    }

    static boolean hasCycle(GraphDirectedByAdjacencyList g) {
        int[] color = new int[g.V];

        for (int i = 0; i < g.V; i++) {
            if (color[i] == WHITE && dfsByIterativeWithColor(g, i, color)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        /*GraphDirectedByAdjacencyList g1 = new GraphDirectedByAdjacencyList(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(1, 3);
        g1.addEdge(2, 4);
        System.out.println(hasCycle(g1));*/

        GraphDirectedByAdjacencyList g2 = new GraphDirectedByAdjacencyList(4);
        g2.addEdge(0, 1);
        g2.addEdge(2, 0);
        g2.addEdge(2, 1);
        g2.addEdge(3, 2);
        System.out.println(hasCycle(g2));
    }
}
