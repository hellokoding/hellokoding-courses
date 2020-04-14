package com.hellokoding.datastructure.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class DFSByIterative {
    static void dfsByIterative(GraphUndirectedByAdjacencyList g, int v) {
        boolean[] visited = new boolean[g.getV()];

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(v);

        while (!stack.isEmpty()) {
            v = stack.pop();

            if (!visited[v]) {
                visited[v] = true;
                System.out.printf("%d ", v);

                for(Integer w : g.getAdjacencyList().get(v)) {
                    stack.push(w);
                }
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

        dfsByIterative(g, 0);
    }
}
