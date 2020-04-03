package com.hellokoding.datastructure;

import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirstSearchOnGraph extends GraphUndirectedByAdjacencyList {
    public BreadthFirstSearchOnGraph(int V) {
        super(V);
    }

    public void breadthFirstSearch(int startingVertex) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[startingVertex] = true;
        queue.offer(startingVertex);

        while (!queue.isEmpty()) {
            Integer currentVertex = queue.poll();
            System.out.printf("%d ", currentVertex);

            for(Integer vertex : adjacencyList.get(currentVertex)) {
                if (!visited[vertex]) {
                    visited[vertex] = true;
                    queue.offer(vertex);
                }
            }
        }
    }

    public static void main(String[] args) {
        BreadthFirstSearchOnGraph graph = new BreadthFirstSearchOnGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        graph.breadthFirstSearch(0);
    }
}
