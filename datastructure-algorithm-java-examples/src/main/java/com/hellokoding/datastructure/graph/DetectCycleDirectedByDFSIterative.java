package com.hellokoding.datastructure.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class DetectCycleDirectedByDFSIterative {
    static boolean dfsByIterative(GraphDirectedByAdjacencyList g) {
        boolean[] visited = new boolean[g.V];
        boolean[] onStack = new boolean[g.V];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < g.V; i++) {
            if (visited[i]) continue;
            stack.push(i);

            while (!stack.isEmpty()) {
                int v = stack.peek();

                if (!visited[v]) {
                    visited[v] = true;
                    onStack[v] = true;
                } else {
                    onStack[v] = false;
                    stack.pop();
                }

                for (Integer w : g.adjacencyList.get(v)) {
                    if (!visited[w]) {
                        stack.push(w);
                    } else if (onStack[w]) {
                        return true;
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
        System.out.println(dfsByIterative(g1));

        GraphDirectedByAdjacencyList g2 = new GraphDirectedByAdjacencyList(4);
        g2.addEdge(0, 1);
        g2.addEdge(2, 0);
        g2.addEdge(2, 1);
        g2.addEdge(3, 2);
        System.out.println(dfsByIterative(g2));
    }
}
