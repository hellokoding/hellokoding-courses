package com.hellokoding.datastructure;

public class DisjoinSetUnionByRank {
    int[] parents, ranks;
    int N;

    DisjoinSetUnionByRank(int N) {
        this.N = N;
        parents = new int[N];
        ranks = new int[N];
        initDisjoinSet();
    }

    void initDisjoinSet() {
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    int findRoot(int x) {
        if (parents[x] != x) {
            parents[x] = findRoot(parents[x]);
        }

        return parents[x];
    }

    void union(int x, int y) {
        int rootOfX = findRoot(x);
        int rootOfY = findRoot(y);

        if (rootOfX == rootOfY) {
            return;
        }

        if (ranks[rootOfX] < ranks[rootOfY]) {
            parents[rootOfX] = rootOfY;
        } else if (ranks[rootOfX] > ranks[rootOfY]) {
            parents[rootOfY] = rootOfX;
        } else {
            parents[rootOfX] = rootOfY;
            ranks[rootOfY] = ranks[rootOfY] + 1;
        }
    }


    public static void main(String[] args) {
        int N = 4;
        DisjoinSetUnionByRank disjoinSet = new DisjoinSetUnionByRank(N);
        disjoinSet.union(0, 1);
        disjoinSet.union(1, 2);

        // Check if 1 is in the same set with 2
        if (disjoinSet.findRoot(1) == disjoinSet.findRoot(2)) {
            System.out.println("1 is in the same set with 2");
        } else {
            System.out.println("1 is not in the same set with 2");
        }

        // Check if 1 is in the same set with 3
        if (disjoinSet.findRoot(1) == disjoinSet.findRoot(3)) {
            System.out.println("1 is in the same set with 3");
        } else {
            System.out.println("1 is not in the same set with 3");
        }
    }
}
