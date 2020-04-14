package com.hellokoding.datastructure.graph;

import java.util.*;

import static com.hellokoding.datastructure.graph.GraphWeightedByAdjacencyList.WeightedVertex;

public class ShortestPathByDijkstraWithMinHeap {
    static final int INFINITE = Integer.MAX_VALUE;
    static final int UNDEFINED = -1;

    static Object[] shortestPathByDijkstra(GraphWeightedByAdjacencyList g, int source) {
        int[] distances = new int[g.getV()];
        int[] predecessors = new int[g.getV()];
        PriorityQueue<WeightedVertex> minHeap = new PriorityQueue<>(g.getV(), Comparator.comparingInt(WeightedVertex::getWeight));

        distances[source] = 0;
        for (int v = 0; v < g.getV(); v++) {
            if (v != source) {
                distances[v] = INFINITE;
                predecessors[v] = UNDEFINED;
            }

            minHeap.add(new WeightedVertex(v, distances[v]));
        }

        while (!minHeap.isEmpty()) {
            WeightedVertex v = minHeap.poll();

            for (WeightedVertex w : g.getAdjacencyList().get(v.vertex)) {
                if (distances[v.vertex] == INFINITE) continue;

                int alt = distances[v.vertex] + w.weight;
                if (alt < distances[w.vertex]) {
                    distances[w.vertex] = alt;
                    predecessors[w.vertex] = v.vertex;
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
            while (u > 0) {
                route.push(u);
                u =  predecessors[u];
            }

            System.out.printf("%-8d%-24d%s%s", v, distances[v], route.toString(), System.lineSeparator());
        }
    }

    public static void main(String[] args) {
        GraphWeightedByAdjacencyList g = new GraphWeightedByAdjacencyList(4);
        g.addEdge(0, 1, 19);
        g.addEdge(2, 0, 15);
        g.addEdge(2, 1, 17);
        g.addEdge(3, 2, 12);
        printResult(shortestPathByDijkstra(g, 3));
        printResult(shortestPathByDijkstra(g, 2));
    }
}
