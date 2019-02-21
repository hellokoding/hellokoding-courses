package com.hellokoding.algorithm;

import com.hellokoding.datastructure.UndirectedGraphByAdjacencyList;

import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirstSearchOnGraph {
    public void breadthFirstSearch(UndirectedGraphByAdjacencyList g, int startingVertex) {
        boolean[] visited = new boolean[g.getV()];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[startingVertex] = true;
        queue.offer(startingVertex);

        while (!queue.isEmpty()) {
            Integer currentVertex = queue.poll();
            System.out.printf("%d ", currentVertex);

            for(Integer vertex : g.getAdjacencyList(currentVertex)) {
                if (!visited[vertex]) {
                    visited[vertex] = true;
                    queue.offer(vertex);
                }
            }
        }
    }

    public static void main(String[] args) {
        UndirectedGraphByAdjacencyList graph = new UndirectedGraphByAdjacencyList(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        new BreadthFirstSearchOnGraph().breadthFirstSearch(graph, 0);
    }
}
