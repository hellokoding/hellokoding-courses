package com.hellokoding.algorithm;

import java.util.HashSet;
import java.util.Set;

public class LongestKDistinctSubstringWindowSliding {
    public static String findDistinctSubstring(char[] a, int startIndex, int k) {
        Set<Character> characters = new HashSet<>();
        String result = "";

        int i = startIndex;
        while (characters.size() <= k || result.contains(String.valueOf(a[i]))) {
            result += a[i];
            characters.add(a[i]);
            i++;
        }

        return result;
    }

    public static String findLongestSubstring(String s, int k) {
        char[] a = s.toCharArray();
        String longestSubstring = findDistinctSubstring(a, 0, k);

        String currentSubstring = "";
        for (int j = 1; j < s.length(); j++) {
            currentSubstring = findDistinctSubstring(a, j, k);
            if (currentSubstring.length() > longestSubstring.length()){
                longestSubstring = currentSubstring;
            }
        }

        return longestSubstring;
    }

    public static void main(String[] args) {
        System.out.println(findLongestSubstring("abcbdbdbbdcdabd", 3));
        //System.out.println(findLongestSubstring("abcbdbdbbdcdabd", 3));
        //System.out.println(findLongestSubstring("abcbdbdbbdcdabd", 5));
    }
}
