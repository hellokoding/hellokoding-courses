package com.hellokoding.datastructure;

import java.util.List;

public class GraphDirectedDetectCycleByDFS extends GraphDirectedByAdjacencyList {
    public GraphDirectedDetectCycleByDFS(int V) {
        super(V);
    }

    private boolean dfs(int v, boolean[] visited, boolean[] stack) {
        if (stack[v]) return true;
        if (visited[v]) return false;

        visited[v] = true;
        stack[v] = true;

        List<Integer> children = adjacencyList.get(v);
        for (Integer c: children) {
            if (dfs(c, visited, stack)) {
                return true;
            }
        }

        stack[v] = false;
        return false;
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[V];
        boolean[] stack = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (dfs(i, visited, stack)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        GraphDirectedDetectCycleByDFS graph = new GraphDirectedDetectCycleByDFS(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        System.out.println(graph.hasCycle());
    }
}
