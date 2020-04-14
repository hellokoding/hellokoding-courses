package com.hellokoding.datastructure.graph;

public class DetectCycleDirectedByDFSRecursiveColor {
    static final int WHITE = 0, GRAY = 1, BLACK = 2;

    static boolean dfsByRecursiveWithColor(GraphDirectedByAdjacencyList g, int v, int[] color) {
        color[v] = GRAY;

        for (Integer w : g.getAdjacencyList().get(v)) {
            if (color[w] == GRAY) {
                return true;
            }

            if (color[w] == WHITE && dfsByRecursiveWithColor(g, w, color)) {
                return true;
            }
        }

        color[v] = BLACK;
        return false;
    }

    static boolean hasCycle(GraphDirectedByAdjacencyList g) {
        int[] color = new int[g.getV()];

        for (int i = 0; i < g.getV(); i++) {
            if (color[i] == WHITE && dfsByRecursiveWithColor(g, i, color)) {
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
