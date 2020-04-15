package com.hellokoding.datastructure.graph;

import java.util.*;

import static com.hellokoding.datastructure.graph.GraphWeightedByAdjacencyList.WeightedVertex;

public class ShortestPathByDijkstra {
    static final int INFINITE = Integer.MAX_VALUE;
    static final int UNDEFINED = -1;

    static int minVertex(Queue<Integer> queue, int[] distance) {
        int minDistance = INFINITE;
        int minVertex = UNDEFINED;

        for (Integer v : queue) {
            if (minDistance > distance[v]) {
                minDistance = distance[v];
                minVertex = v;
            }
        }

        return minVertex;
    }

    static Object[] shortestPathByDijkstra(GraphWeightedByAdjacencyList g, int source) {
        int[] distances = new int[g.getV()];
        int[] predecessors = new int[g.getV()];
        Queue<Integer> queue = new ArrayDeque<>();

        for (int v = 0; v < g.getV(); v++) {
            distances[v] = INFINITE;
            predecessors[v] = UNDEFINED;
            queue.offer(v);
        }
        distances[source] = 0;

        while (!queue.isEmpty()) {
            int u = minVertex(queue, distances);
            if (u == UNDEFINED)  break;
            queue.remove(u);

            for (WeightedVertex v : g.getAdjacencyList().get(u)) {
                if (distances[u] == INFINITE) continue;

                int alt = distances[u] + v.weight;
                if (alt < distances[v.vertex]) {
                    distances[v.vertex] = alt;
                    predecessors[v.vertex] = u;
                }
            }
        }

        return new Object[]{distances, predecessors};
    }

    static void printResult(Object[] paths) {
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
        GraphWeightedByAdjacencyList g = new GraphWeightedByAdjacencyList(4);
        g.addEdge(0, 1, 2);
        g.addEdge(2, 0, 1);
        g.addEdge(2, 1, 5);
        g.addEdge(3, 2, 4);
        printResult(shortestPathByDijkstra(g, 3));
        printResult(shortestPathByDijkstra(g, 2));
    }
}
