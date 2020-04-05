package com.hellokoding.datastructure.graph;

import java.util.List;

public class DetectCycleDirectedByDFSRecursive {
    static boolean dfs(GraphDirectedByAdjacencyList g, int v, boolean[] visited, boolean[] stack) {
        if (stack[v]) return true;
        if (visited[v]) return false;

        visited[v] = true;
        stack[v] = true;

        List<Integer> children = g.adjacencyList.get(v);
        for (Integer c: children) {
            if (dfs(g, c, visited, stack)) {
                return true;
            }
        }

        stack[v] = false;
        return false;
    }

    static boolean hasCycle(GraphDirectedByAdjacencyList g) {
        boolean[] visited = new boolean[g.V];
        boolean[] stack = new boolean[g.V];

        for (int i = 0; i < g.V; i++) {
            if (dfs(g, i, visited, stack)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        GraphDirectedByAdjacencyList g = new GraphDirectedByAdjacencyList(3);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        System.out.println(hasCycle(g));
    }
}
