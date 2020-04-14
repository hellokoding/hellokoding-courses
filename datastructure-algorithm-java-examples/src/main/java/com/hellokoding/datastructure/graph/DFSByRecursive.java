package com.hellokoding.datastructure.graph;

public class DFSByRecursive {

    static void dfs(GraphUndirectedByAdjacencyList g, int v, boolean[] visited) {
        visited[v] = true;
        System.out.printf("%d ", v);

        for (Integer w : g.getAdjacencyList().get(v)) {
            if (!visited[w]) {
                dfs(g, w, visited);
            }
        }
    }

    static void traversal(GraphUndirectedByAdjacencyList g) {
        boolean[] visited = new boolean[g.getV()];

        for (int i = 0; i < g.getV(); i++) {
            if (!visited[i]) {
                dfs(g, i, visited);
            }
        }
    }

    public static void main(String[] args) {
        GraphUndirectedByAdjacencyList g = new GraphUndirectedByAdjacencyList(5);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 0);
        g.addEdge(1, 3);
        g.addEdge(2, 4);

        traversal(g);
    }
}
