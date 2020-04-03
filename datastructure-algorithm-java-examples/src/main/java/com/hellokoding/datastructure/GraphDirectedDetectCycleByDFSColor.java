package com.hellokoding.datastructure;

import java.util.ArrayDeque;
import java.util.Deque;

public class GraphDirectedDetectCycleByDFSColor extends GraphDirectedByAdjacencyList {
    static final int WHITE = 0, GRAY = 1, BLACK = 2;
    public GraphDirectedDetectCycleByDFSColor(int V) {
        super(V);
    }

    public boolean dfsWithColor(int startingVertex) {
        int[] color = new int[V];
        color[startingVertex] = GRAY;

        Deque<Integer> stack = new ArrayDeque<>(V);
        stack.push(startingVertex);

        while (!stack.isEmpty()){
            int v = stack.peek();
            for (int adj : adjacencyList.get(v)) {
                if (color[adj] == GRAY) {
                    return true;
                } else if (color[adj] == WHITE) {
                    color[adj] = GRAY;
                    stack.push(adj);
                } else {
                    color[adj] = BLACK;
                    stack.pop();
                }
            }
        }

        return false;
    }

    private boolean dfsWithColorByRecursion(int v, int[] color) {
        color[v] = GRAY;

        for (Integer adj : adjacencyList.get(v)) {
            if (color[v] == GRAY) {
                return true;
            }

            if (color[v] == WHITE && dfsWithColorByRecursion(adj, color)){
                return true;
            }
        }

        color[v] = BLACK;
        return false;
    }

    public boolean hasCycleByRecursion() {
        int[] color = new int[V];

        for (int i = 0; i < V; i++) {
            if (color[i] == WHITE && dfsWithColorByRecursion(i, color)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        GraphDirectedDetectCycleByDFSColor graph = new GraphDirectedDetectCycleByDFSColor(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        System.out.println(graph.dfsWithColor(0));
    }
}
