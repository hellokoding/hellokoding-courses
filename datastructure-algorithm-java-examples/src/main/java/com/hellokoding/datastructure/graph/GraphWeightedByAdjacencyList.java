package com.hellokoding.datastructure.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphWeightedByAdjacencyList {
    private int V;
    private List<List<WeightedVertex>> adjacencyList;

    public GraphWeightedByAdjacencyList(int V) {
        this.V = V;

        adjacencyList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public Integer getV() {
        return this.V;
    }

    public List<List<WeightedVertex>> getAdjacencyList() {
        return this.adjacencyList;
    }

    public void addEdge(int source, int dest, int weight) {
        adjacencyList.get(source).add(new WeightedVertex(dest, weight));
    }

    public void printAdjacencyList() {
        for (int i = 0; i < V; i++) {
            System.out.printf("Adjacency list of vertex %d is %s %s", i,
                adjacencyList.get(i), System.lineSeparator());
        }
    }

    static class WeightedVertex {
        final Integer vertex, weight;

        public WeightedVertex(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int getWeight() {
            return this.weight;
        }

        public String toString() {
            return String.format("%d (weight %d)", vertex, weight);
        }
    }

    public static void main(String[] args) {
        GraphWeightedByAdjacencyList g = new GraphWeightedByAdjacencyList(4);
        g.addEdge(0, 1, 19);
        g.addEdge(2, 0, 15);
        g.addEdge(2, 1, 17);
        g.addEdge(3, 2, 12);
        g.printAdjacencyList();
    }
}
