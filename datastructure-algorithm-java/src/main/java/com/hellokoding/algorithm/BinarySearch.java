package com.hellokoding.algorithm;

public class BinarySearch {
    public static boolean binarySearch(int target, int[] nums) {
        int floorIndex = -1;
        int ceilingIndex = nums.length;

        while (floorIndex + 1 < ceilingIndex) {
            int distance = ceilingIndex - floorIndex;
            int halfDistance = distance / 2;
            int guessIndex = floorIndex + halfDistance;
            int guessValue = nums[guessIndex];

            if (guessValue == target) return true;
            if (guessValue > target) ceilingIndex = guessIndex;
            else floorIndex = guessIndex;
        }

        return false;
    }
}
