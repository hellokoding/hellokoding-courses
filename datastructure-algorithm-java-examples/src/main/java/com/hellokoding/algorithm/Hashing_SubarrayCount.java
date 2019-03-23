package com.hellokoding.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Hashing_SubarrayCount {
    public static void printSubarrayCount(int[] A, int N, int S) {
        Map<Integer, Integer> map = new HashMap<>();
        int currentSum = 0;
        int count = 0;
        for(int i=0; i<N; i++) {
            currentSum += A[i];
            map.compute(currentSum, (key, value) -> value == null ? 1 : value++);

            if (currentSum == S) {
                count++;
            }

            if (map.containsKey(currentSum - S)){
                count += map.get(currentSum - S);
            }
        }

        System.out.println(count);

    }

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int S = Integer.parseInt(br.readLine());

            printSubarrayCount(A, N, S);
        }
    }
}
