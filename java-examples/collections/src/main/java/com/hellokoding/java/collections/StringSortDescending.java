package com.hellokoding.java.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

public class StringSortDescending {
    static String sortDesc(String str) {
        // convert String to Character array
        Character[] arr = str.chars().mapToObj(c -> (char)c).toArray(Character[]::new);

        // sort Character array in descending order
        Arrays.sort(arr, Comparator.reverseOrder());

        // String join Character array
        return Arrays.stream(arr).map(Objects::toString).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        String str = "bac";
        System.out.println(sortDesc(str));
    }
}
