package com.hellokoding.datastructure.graph;

import java.util.List;

public class DetectCycleUndirected extends GraphUndirectedByAdjacencyList {
    public DetectCycleUndirected(int V) {
        super(V);
    }

    private boolean dfs(int v, boolean[] visited, int parent) {
        visited[v] = true;

        List<Integer> children = getAdjacencyList().get(v);
        for (Integer c: children) {
            if (!visited[c] && dfs(c, visited, v)) {
                return true;
            } else if (v != parent) {
                return true;
            }
        }

        return false;
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[getV()];

        for (int i = 0; i < getV(); i++) {
            if (!visited[i] && dfs(i, visited, -1)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        DetectCycleUndirected graph = new DetectCycleUndirected(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        System.out.println(graph.hasCycle());
    }
}
