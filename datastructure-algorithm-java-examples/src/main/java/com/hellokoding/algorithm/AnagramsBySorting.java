package com.hellokoding.algorithm;

import java.util.Arrays;

public class AnagramsBySorting {
    static boolean areAnagrams(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] a1 = s.toCharArray();
        char[] a2 = t.toCharArray();

        Arrays.sort(a1);
        Arrays.sort(a2);

        return Arrays.equals(a1, a2);
    }

    public static void main(String[] args) {
        System.out.println(areAnagrams("listen", "silent"));
        System.out.println(areAnagrams("eleven plus two", "twelve plus one"));
        System.out.println(areAnagrams("rat", "car"));
    }
}
