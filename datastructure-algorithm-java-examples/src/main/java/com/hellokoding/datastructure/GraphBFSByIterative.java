package com.hellokoding.datastructure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class GraphBFSByIterative {
    public static void bfsByIterative(GraphUndirectedByAdjacencyList g, int v) {
        boolean[] visited = new boolean[g.V];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(v);

        while (!queue.isEmpty()) {
            v = queue.poll();

            if (!visited[v]) {
                visited[v] = true;
                System.out.printf("%d ", v);

                for (Integer w : g.adjacencyList.get(v)) {
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
