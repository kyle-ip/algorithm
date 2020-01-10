package com.ywh.problem.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 同构字符串
 * [字符串]
 *
 * @author ywh
 * @since 10/01/2020
 */
public class LeetCode205 {

    /**
     * Time: O(1), Space: O(n)
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> hash1 = new HashMap<>(), hash2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (!hash1.containsKey(s.charAt(i))) {
                hash1.put(s.charAt(i), i);
            }
            if (!hash2.containsKey(t.charAt(i))) {
                hash2.put(t.charAt(i), i);
            }
            if (!hash1.get(s.charAt(i)).equals(hash2.get(t.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Time: O(1), Space: O(n)
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic2(String s, String t) {
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if(s.indexOf(ch1[i]) != t.indexOf(ch2[i])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode205 s = new LeetCode205();
        System.out.println(s.isIsomorphic("egg", "add"));
        System.out.println(s.isIsomorphic("foo", "bar"));
        System.out.println(s.isIsomorphic("paper", "title"));

    }
}
