package com.hellokoding.datastructure.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TopologicalSortByDFSIterativeColor {
    static final int WHITE = 0, GRAY = 1, BLACK = 2;

    static List<Integer> topologicalSort(GraphDirectedByAdjacencyList g) {
        int[] color = new int[g.getV()];
        Deque<Integer> stack = new ArrayDeque<>(g.getV());
        Deque<Integer> sorted = new ArrayDeque<>(g.getV());

        for (int i = 0; i < g.getV(); i++) {
            if (color[i] != WHITE) continue;
            stack.push(i);

            while (!stack.isEmpty()) {
                int v = stack.peek();

                if (color[v] == WHITE) {
                    color[v] = GRAY;
                } else {
                    color[v] = BLACK;
                    sorted.push(stack.pop());
                }

                for (int w : g.getAdjacencyList().get(v)) {
                    if (color[w] == GRAY) {
                        // Found a cycle
                        return new ArrayList<>();
                    } else if (color[w] == WHITE) {
                        stack.push(w);
                    }
                }
            }
        }

        return new ArrayList<>(sorted);
    }

    static void printResult(List<Integer> sorted) {
        if (sorted.isEmpty()) {
            System.out.println("There are no topological orderings as the input graph is cyclic");
        } else {
            sorted.forEach((x) -> System.out.printf("%d ", x));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphDirectedByAdjacencyList g1 = new GraphDirectedByAdjacencyList(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(1, 3);
        g1.addEdge(2, 4);
        printResult(topologicalSort(g1));

        GraphDirectedByAdjacencyList g2 = new GraphDirectedByAdjacencyList(4);
        g2.addEdge(0, 1);
        g2.addEdge(2, 0);
        g2.addEdge(2, 1);
        g2.addEdge(3, 2);
        printResult(topologicalSort(g2));
    }
}
