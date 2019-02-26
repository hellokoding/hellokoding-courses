package com.hellokoding.algorithm;

public class LISLength {
    static int findLengthOfLIS(int[] arr) {
        int N = arr.length;
        int[] lis = new int[N];

        for (int i = 0; i < N; i++) {
            lis[i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && (lis[i] < lis[j] + 1)) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        int max = lis[0];
        for (int i = 1; i < N; i++) {
            if (max < lis[i]) {
                max = lis[i];
            }
        }

        return max;
    }


    public static void main(String[] args) {
        int arr[] = {3, 2, 6, 4, 5, 1};
        System.out.println("Length of LIS: " + findLengthOfLIS(arr));
    }
}
