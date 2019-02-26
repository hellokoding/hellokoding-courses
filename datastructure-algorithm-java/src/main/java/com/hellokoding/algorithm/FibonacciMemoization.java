package com.hellokoding.algorithm;

public class FibonacciMemoization {
    int fibonacci(int[] m, int n) {
        if (m[n] == 0) {
            if (n < 2) m[n] = n;
            else m[n] = fibonacci(m, n-1) + fibonacci(m, n-2);
        }

        return m[n];
    }

    public static void main(String[] args) {
        int n = 10;
        int[] m = new int[n+1];
        System.out.println(new FibonacciMemoization().fibonacci(m, n));
    }
}
