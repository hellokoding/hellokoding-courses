package com.hellokoding.algorithm;

public class FibonacciMemoization {
    int[] cache;

    FibonacciMemoization(int[] cache){
        this.cache = cache;
    }

    int fibonacci(int n) {
        if (cache[n] == 0) {
            if (n < 2) cache[n] = n;
            else cache[n] = fibonacci(n-1) + fibonacci( n-2);
        }

        return cache[n];
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(new FibonacciMemoization(new int[n+1]).fibonacci(n));
    }
}
