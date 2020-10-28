package com.hellokoding.algorithm;

import java.util.HashMap;
import java.util.Map;

public class AnagramsByHistogramming {
    static boolean areAnagrams(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> histogram = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            histogram.compute(s.charAt(i), (k,v) -> (v == null ? 0 : v) + 1);
            histogram.compute(t.charAt(i), (k,v) -> (v == null ? 0 : v) - 1);
        }

        for (int count : histogram.values()) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(areAnagrams("listen", "silent"));
        System.out.println(areAnagrams("eleven plus two", "twelve plus one"));
        System.out.println(areAnagrams("rat", "car"));
    }
}
