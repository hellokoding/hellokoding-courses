// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");
package com.hellokoding.algorithm;
class FairIndexOf2Arrays {
    public static int findFairIndex(int[] A, int[] B) {
        int N = A.length;
        int sumA = getSum(A);
        int sumB = getSum(B);
        int sumSubA = 0, sumSubB = 0;
        //int ans = 0;

        for(int i=0; i<N-1; i++) {
            sumSubA += A[i];
            sumSubB += B[i];

            if (sumSubA == sumSubB
                && (sumA-sumSubA == sumB-sumSubB)
                && (sumSubA == sumA-sumSubA)
                && (sumSubB == sumB-sumSubB)) {
                return i+1;
            }
        }

        return 0;
    }

    public static int getSum(int[] A) {
        int sum = 0;
        for(int num : A) {
            sum += num;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(findFairIndex(new int[]{2, -2, -3, 3}, new int[]{0, 0, 4, -4}));
    }
}
