package com.hellokoding.algorithm;

import com.hellokoding.datastructure.UndirectedGraphByAdjacencyList;

import java.util.ArrayDeque;
import java.util.Deque;

public class DepthFirstSearchOnGraph {
    public void depthFirstSearch(UndirectedGraphByAdjacencyList g, int startingVertex) {
        boolean[] visited = new boolean[g.getV()];
        Deque<Integer> stack = new ArrayDeque<>();

        visited[startingVertex] = true;
        stack.push(startingVertex);

        while (!stack.isEmpty()) {
            Integer currentVertex = stack.pop();
            System.out.printf("%d ", currentVertex);

            for(Integer vertex : g.getAdjacencyList(currentVertex)) {
                if (!visited[vertex]) {
                    visited[vertex] = true;
                    stack.push(vertex);
                }
            }
        }
    }

    public static void main(String[] args) {
        UndirectedGraphByAdjacencyList graph = new UndirectedGraphByAdjacencyList(5);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 0);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        new DepthFirstSearchOnGraph().depthFirstSearch(graph, 0);
    }
}
