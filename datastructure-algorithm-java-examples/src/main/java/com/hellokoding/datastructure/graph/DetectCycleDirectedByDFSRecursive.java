package com.hellokoding.datastructure.graph;

import java.util.List;

public class DetectCycleDirectedByDFSRecursive {
    static boolean dfsByRecursive(GraphDirectedByAdjacencyList g, int v, boolean[] visited, boolean[] onStack) {
        if (onStack[v]) return true;
        if (visited[v]) return false;

        visited[v] = true;
        onStack[v] = true;

        List<Integer> children = g.getAdjacencyList().get(v);
        for (Integer c: children) {
            if (dfsByRecursive(g, c, visited, onStack)) {
                return true;
            }
        }

        onStack[v] = false;
        return false;
    }

    static boolean hasCycle(GraphDirectedByAdjacencyList g) {
        boolean[] visited = new boolean[g.getV()];
        boolean[] onStack = new boolean[g.getV()];

        for (int i = 0; i < g.getV(); i++) {
            if (dfsByRecursive(g, i, visited, onStack)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        GraphDirectedByAdjacencyList g1 = new GraphDirectedByAdjacencyList(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(1, 3);
        g1.addEdge(2, 4);
        System.out.println(hasCycle(g1));

        GraphDirectedByAdjacencyList g2 = new GraphDirectedByAdjacencyList(4);
        g2.addEdge(0, 1);
        g2.addEdge(2, 0);
        g2.addEdge(2, 1);
        g2.addEdge(3, 2);
        System.out.println(hasCycle(g2));
    }
}
