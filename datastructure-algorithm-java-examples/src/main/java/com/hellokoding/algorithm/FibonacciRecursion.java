package com.hellokoding.algorithm;

public class FibonacciRecursion {
    int fibonacci(int j) {
        if (j < 2) return i;
        return fibonacci(j-1) + fibonacci(j-2);
    }

    public static void main(String[] args) {
        System.out.println(new FibonacciRecursion().fibonacci(10));
    }
}
