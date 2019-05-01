package com.hellokoding.algorithm;

import java.util.Arrays;
import java.util.Comparator;

public class DP_BoxStacking {
    static class Box {
        int height;
        int width;
        int depth;
        int area;

        Box(int height, int width, int depth) {
            this.height = height;
            this.width = width;
            this.depth = depth;
            this.area = this.width * this.depth;
        }
    }

    static int findMaxHeight(Box[] arr, int N) {
        Box[] boxes = new Box[N*3];

        for (int i = 0; i < N; i++) {
            Box current = arr[i];
            boxes[i*3] = new Box(current.height, Math.max(current.width, current.depth), Math.min(current.width, current.depth));
            boxes[i*3 + 1] = new Box(current.width, Math.max(current.height, current.depth), Math.min(current.height, current.depth));
            boxes[i*3 + 2] = new Box(current.depth, Math.max(current.height, current.width), Math.min(current.height, current.width));
        }

        Arrays.sort(boxes, Comparator.comparing(x -> x.area, Comparator.reverseOrder()));
        N *= 3;

        int[] maxHeights = new int[N];
        for (int i = 0; i < N; i++) {
            maxHeights[i] = boxes[i].height;
        }

        for (int i = 0; i < N; i++) {
            int maxHeightBeforeI = 0;
            for (int j = 0; j < i; j++) {
                if (boxes[j].width > boxes[i].width && boxes[j].depth > boxes[i].depth)
                    maxHeightBeforeI =  Math.max(maxHeightBeforeI, maxHeights[j]);
            }
            maxHeights[i] = maxHeightBeforeI + boxes[i].height;
        }

        return Arrays.stream(maxHeights).max().getAsInt();
    }

    public static void main(String[] args) {
        Box[] arr = new Box[4];
        arr[0] = new Box(4, 6, 7);
        arr[1] = new Box(1, 2, 3);
        arr[2] = new Box(4, 5, 6);
        arr[3] = new Box(10, 12, 32);

        System.out.println(findMaxHeight(arr, 4));
    }
}
