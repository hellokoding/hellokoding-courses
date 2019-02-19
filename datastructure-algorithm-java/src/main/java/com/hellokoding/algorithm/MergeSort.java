package com.hellokoding.algorithm;

import java.util.Arrays;

public class MergeSort {
    public static int[] mergeSort(int[] arrayToSort) {
        // base case
        if (arrayToSort.length < 2) {
            return arrayToSort;
        }

        // STEP 1: divide the array in half
        int midIndex = arrayToSort.length / 2;
        int[] left = Arrays.copyOfRange(arrayToSort, 0, midIndex);
        int[] right = Arrays.copyOfRange(arrayToSort, midIndex, arrayToSort.length);

        // STEP 2: sort each half
        int[] sortedLeft = mergeSort(left);
        int[] sortedRight = mergeSort(right);

        // STEP 3: merge the sorted halves
        int[] sortedArray = new int[arrayToSort.length];

        int currentLeftIndex = 0;
        int currentRightIndex = 0;

        for (int currentSortedIndex = 0; currentSortedIndex < arrayToSort.length; currentSortedIndex++) {
            if (currentLeftIndex < sortedLeft.length && (currentRightIndex >= sortedRight.length || sortedLeft[currentLeftIndex] < sortedRight[currentRightIndex])) {
                sortedArray[currentSortedIndex] = sortedLeft[currentLeftIndex];
                currentLeftIndex++;
            } else {
                sortedArray[currentSortedIndex] = sortedRight[currentRightIndex];
                currentRightIndex++;
            }
        }

        return sortedArray;
    }
}
