package com.hellokoding.algorithm;

public class AnagramsByHistogramming {
    static boolean areAnagrams(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        s = s.replaceAll("\\s", "");
        t = t.replaceAll("\\s", "");

        int[] frequencies = new int[26];

        for (int i = 0; i < s.length(); i++) {
            frequencies[s.charAt(i) - 'a']++;
            frequencies[t.charAt(i) - 'a']--;
        }

        for (int count : frequencies) {
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
