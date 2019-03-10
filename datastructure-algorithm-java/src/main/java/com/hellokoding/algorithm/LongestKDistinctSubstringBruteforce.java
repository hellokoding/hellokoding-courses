package com.hellokoding.algorithm;

import java.util.HashSet;
import java.util.Set;

public class LongestKDistinctSubstringBruteforce {
    public static String findLongestSubstring(String s, int k) {
        char[] a = s.toCharArray();
        String longestSubstring = "";
        String currentSubstring = "";
        Set<Character> characters = new HashSet<>();

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (characters.size() < k) {
                    characters.add(a[j]);
                    currentSubstring += a[j];
                } else if (characters.size() == k) {
                    if (currentSubstring.contains(String.valueOf(a[j]))) {
                        currentSubstring += a[j];
                    } else {
                        if (longestSubstring.length() < currentSubstring.length()) {
                            longestSubstring = currentSubstring;
                        }

                        break;
                    }
                }
            }

            if (currentSubstring.equals(s)) return s;
            currentSubstring = "";
            characters = new HashSet<>();
        }

        return longestSubstring;
    }

    public static void main(String[] args) {
        System.out.println(findLongestSubstring("abcbdbdbbdcdabd", 5));
        //System.out.println(findLongestSubstring("abcbdbdbbdcdabd", 3));
        //System.out.println(findLongestSubstring("abcbdbdbbdcdabd", 5));
    }
}
