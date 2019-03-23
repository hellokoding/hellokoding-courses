package com.hellokoding.algorithm;

import java.util.HashMap;
import java.util.Map;

public class WindowSliding_GivenString_FindMinSubstr {
    public static String findMinSubstr(String s, String t) {
        Map<Character, Integer> tChars = new HashMap<>();
        for(char c : t.toCharArray()) {
            tChars.compute(c, (key, value) -> value == null ? 1 : ++value);
        }

        Map<Character, Integer> sChars = new HashMap<>();
        String windowSubstr = "";
        String minSubstr = "";
        int i = 0;
        int j = 0;
        int matched = 0;

        while(i < s.length()) {
            Character c;
            while(j < s.length() && matched < tChars.size()) {
                c = s.charAt(j++);
                sChars.compute(c, (key, value) -> value == null ? 1 : ++value);
                windowSubstr += c;

                if (tChars.containsKey(c) && tChars.get(c).equals(sChars.get(c))) matched++;
            }

            if (matched == tChars.size() && (minSubstr.equals("") || windowSubstr.length() < minSubstr.length())){
                minSubstr = windowSubstr;
            }

            c = windowSubstr.charAt(0);
            sChars.compute(c, (key, value) -> --value);
            if (tChars.containsKey(c) && tChars.get(c) > sChars.get(c)) matched--;
            windowSubstr = windowSubstr.substring(1);
            i++;
        }

        return minSubstr;
    }

    public static void main(String[] args) {
        System.out.println(findMinSubstr("ADOBECODEBANC", "ABC"));
        System.out.println(findMinSubstr("aaaaaaaaaaaabbbbbcdd", "abcdd"));
        System.out.println(findMinSubstr("a", "aa"));
    }
}
