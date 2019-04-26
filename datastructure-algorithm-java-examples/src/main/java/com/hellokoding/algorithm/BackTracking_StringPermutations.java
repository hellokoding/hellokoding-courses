package com.hellokoding.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BackTracking_StringPermutations {
    List<String> result = new ArrayList<>();

    String swap(String str, int i, int j) {
        char[] arr = str.toCharArray();

        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return String.valueOf(arr);
    }

    void enumerate(String str, int startIndex) {
        if (startIndex == str.length()-1) {
            result.add(str);
            return;
        }

        for(int i=startIndex; i<str.length(); i++) {
            str = swap(str, startIndex, i);
            enumerate(str, startIndex+1);
            str = swap(str, startIndex, i); // backtracking
        }
    }

    public static void main(String[] args) {
        String str = "ABC";

        BackTracking_StringPermutations permutations = new BackTracking_StringPermutations();
        permutations.enumerate(str, 0);

        System.out.println(permutations.result.stream().collect(Collectors.joining(" ")));
    }
}
