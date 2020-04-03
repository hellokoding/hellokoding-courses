package com.hellokoding.datastructure;

import java.util.ArrayDeque;
import java.util.Deque;

public class DepthFirstSearchOnGraph extends GraphUndirectedByAdjacencyList {
    public DepthFirstSearchOnGraph(int V) {
        super(V);
    }

    public void depthFirstSearch(int startingVertex) {
        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();

        visited[startingVertex] = true;
        stack.push(startingVertex);

        while (!stack.isEmpty()) {
            Integer currentVertex = stack.pop();
            System.out.printf("%d ", currentVertex);

            for(Integer vertex : adjacencyList.get(currentVertex)) {
                if (!visited[vertex]) {
                    visited[vertex] = true;
                    stack.push(vertex);
                }
            }
        }
    }

    public static void main(String[] args) {
        DepthFirstSearchOnGraph graph = new DepthFirstSearchOnGraph(5);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 0);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        graph.depthFirstSearch(0);
    }
}
