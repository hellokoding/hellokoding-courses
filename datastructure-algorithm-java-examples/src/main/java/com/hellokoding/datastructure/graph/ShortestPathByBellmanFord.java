package com.hellokoding.datastructure.graph;

import java.util.*;

public class ShortestPathByBellmanFord {
    static final int INFINITE = Integer.MAX_VALUE;
    static final int UNDEFINED = -1;

    static Object[] shortestPathByBellmanFord(GraphWeightedByAdjacencyList g, int source) {
        int[] distances = new int[g.getV()];
        int[] predecessors = new int[g.getV()];

        for (int v = 0; v < g.getV(); v++) {
            distances[v] = INFINITE;
            predecessors[v] = UNDEFINED;
        }
        distances[source] = 0;

        for (int i = 1; i < g.getV(); i++) {
            for (int u = 0; u < g.getV(); u++) {
                for (GraphWeightedByAdjacencyList.WeightedVertex v : g.getAdjacencyList().get(u)) {
                    if (distances[u] == INFINITE) continue;
                    int alt = distances[u] + v.weight;
                    if (alt < distances[v.vertex]) {
                        distances[v.vertex] = alt;
                        predecessors[v.vertex] = u;
                    }
                }
            }
        }

        for (int u = 0; u < g.getV(); u++) {
            for (GraphWeightedByAdjacencyList.WeightedVertex v : g.getAdjacencyList().get(u)) {
                if (distances[u] == INFINITE) continue;
                int alt = distances[u] + v.weight;
                if (alt < distances[v.vertex]) {
                    System.out.println("The input graph contains a negative-weight cycle");
                    return new Object[]{};
                }
            }
        }

        return new Object[]{distances, predecessors};
    }

    static void printResult(Object[] paths) {
        if (paths.length == 0) return;

        int[] distances = (int[]) paths[0];
        int[] predecessors = (int[]) paths[1];

        System.out.println("Vertex\tDistance from source\tRoute from source");
        for (int v = 0; v < distances.length; v++) {
            if (distances[v] == INFINITE) {
                System.out.printf("%-8s%-24s%s%s", v, "N/A", "N/A", System.lineSeparator());
                continue;
            }

            Deque<Integer> route = new ArrayDeque<>();
            route.push(v);
            int u = predecessors[v];
            while (u >= 0) {
                route.push(u);
                u =  predecessors[u];
            }

            System.out.printf("%-8d%-24d%s%s", v, distances[v], route.toString(), System.lineSeparator());
        }
    }

    public static void main(String[] args) {
        GraphWeightedByAdjacencyList g1 = new GraphWeightedByAdjacencyList(4);
        g1.addEdge(0, 1, -2);
        g1.addEdge(2, 0, 1);
        g1.addEdge(2, 1, 3);
        g1.addEdge(3, 2, 4);
        printResult(shortestPathByBellmanFord(g1, 3));

        GraphWeightedByAdjacencyList g2 = new GraphWeightedByAdjacencyList(4);
        g2.addEdge(0, 1, -5);
        g2.addEdge(2, 0, 1);
        g2.addEdge(1, 2, 3);
        g2.addEdge(3, 2, 4);
        printResult(shortestPathByBellmanFord(g2, 3));
    }
}
