package com.hellokoding.datastructure;

public class Array2D {
    void traversal(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.println(A[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] A = {{9, 2, 6, 8}, {5, 7, 1, 3}};
        new Array2D().traversal(A);
    }
}
