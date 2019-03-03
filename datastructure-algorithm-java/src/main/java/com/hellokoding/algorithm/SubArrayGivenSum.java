package com.hellokoding.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SubArrayGivenSum {
    public static void printSubArray(int[] A, int N, int S) {
        for(int i=0; i<N; i++) {
            int currentSum = A[i];
            for(int j=i+1; j<=N; j++) {
                if (currentSum == S) {
                    System.out.println((i+1) + " " + j);
                    return;
                } else if (currentSum > S || j==N) {
                    break;
                }
                currentSum += A[j];
            }
        }

        System.out.println(-1);
    }

    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            String[] ns = br.readLine().split(" ");
            int N = Integer.parseInt(ns[0]);
            int S = Integer.parseInt(ns[1]);
            int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            printSubArray(A, N, S);
        }
    }
}
