package com.hellokoding.datastructure.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class BFSByIterative {
    public static void bfsByIterative(GraphUndirectedByAdjacencyList g, int v) {
        boolean[] visited = new boolean[g.getV()];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(v);

        while (!queue.isEmpty()) {
            v = queue.poll();

            if (!visited[v]) {
                visited[v] = true;
                System.out.printf("%d ", v);

                for (Integer w : g.getAdjacencyList().get(v)) {
                    queue.offer(w);
                }
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

        bfsByIterative(g, 0);
    }
}
