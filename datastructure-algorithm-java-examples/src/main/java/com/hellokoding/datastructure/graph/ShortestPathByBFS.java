package com.hellokoding.datastructure.graph;

import java.util.*;

public class ShortestPathByBFS {
    static final int INFINITE = Integer.MAX_VALUE;
    static final int UNDEFINED = -1;

    public static Object[] shortestPathByBFS(GraphDirectedByAdjacencyList g, int source) {
        int[] distances = new int[g.getV()];
        int[] predecessors = new int[g.getV()];

        for (int v = 0; v < g.getV(); v++) {
            distances[v] = INFINITE;
            predecessors[v] = UNDEFINED;
        }
        distances[source] = 0;

        boolean[] visited = new boolean[g.getV()];
        visited[source] = true;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : g.getAdjacencyList().get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    distances[v] = distances[u] + 1;
                    predecessors[v] = u;
                    queue.offer(v);
                }
            }
        }

        return new Object[]{distances, predecessors};
    }

    static void printResult(Object[] paths) {
        int[] distancesFromSource = (int[]) paths[0];
        int[] predecessorsOfSource = (int[]) paths[1];

        System.out.println("Vertex\tDistance from source\tRoute from source");
        for (int v = 0; v < distancesFromSource.length; v++) {
            if (distancesFromSource[v] == INFINITE) {
                System.out.printf("%-8s%-24s%s%s", v, "N/A", "N/A", System.lineSeparator());
                continue;
            }

            Deque<Integer> route = new ArrayDeque<>();
            route.push(v);
            int u = predecessorsOfSource[v];
            while (u >= 0) {
                route.push(u);
                u =  predecessorsOfSource[u];
            }

            System.out.printf("%-8d%-24d%s%s", v, distancesFromSource[v], route.toString(), System.lineSeparator());
        }
    }

    public static void main(String[] args) {
        GraphDirectedByAdjacencyList g = new GraphDirectedByAdjacencyList(4);
        g.addEdge(0, 1);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(3, 2);
        printResult(shortestPathByBFS(g, 3));
        printResult(shortestPathByBFS(g, 2));
    }
}
