package com.hellokoding.datastructure;

public class GraphDFSByRecursive {

    static void dfs(GraphUndirectedByAdjacencyList g, int v, boolean[] visited) {
        visited[v] = true;
        System.out.printf("%d ", v);

        for (Integer w : g.adjacencyList.get(v)) {
            if (!visited[w]) {
                dfs(g, w, visited);
            }
        }
    }

    static void traversal(GraphUndirectedByAdjacencyList g) {
        boolean[] visited = new boolean[g.V];

        for (int i = 0; i < g.V; i++) {
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
