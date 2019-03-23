package com.hellokoding.datastructure;

public class Array1D {
    void traversal(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.println(A[i]);
        }
    }

    public static void main(String[] args) {
        int[] A = {9, 2, 6, 8};
        new Array1D().traversal(A);
    }
}
