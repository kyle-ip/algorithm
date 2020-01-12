package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 查找字符串中所有变位词
 * [哈希表] [字符串]
 *
 * @author ywh
 * @since 12/01/2020
 */
public class LeetCode438 {

    /**
     *
     *
     * @param sc
     * @param pc
     * @return
     */
    private boolean equals(char[] sc, char[] pc) {
        for (int i = 0; i < sc.length; ++i) {
            if (sc[i] != pc[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Time: O(n*k), Space: O(k)
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }
        int sLen = s.length(), pLen = p.length();
        char[] pc = new char[26], sc = new char[26];
        for (int i = 0; i < pLen; ++i) {
            pc[p.charAt(i) - 'a']++;
            sc[s.charAt(i) - 'a']++;
        }

        if (equals(sc, pc)) {
            result.add(0);
        }
        for (int i = pLen; i < sLen; ++i) {
            sc[s.charAt(i) - 'a']++;
            sc[s.charAt(i-pLen) - 'a']--;
            if (equals(sc, pc)) {
                result.add(i-pLen+1);
            }
        }
        return result;
    }

    /**
     * Time: O(n), Space: O(k)
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramsOn(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }
        int sLen = s.length(), pLen = p.length(), left = 0, right = 0;
        char[] pc = new char[26];
        for (int i = 0; i < pLen; ++i) {
            pc[p.charAt(i) - 'a']++;
        }

        while (right < sLen) {
            if (pc[s.charAt(right) - 'a'] > 0) {
                pc[s.charAt(right) - 'a']--;
                ++right;
            } else {
                pc[s.charAt(left) - 'a']++;
                ++left;
            }
            if (right - left == pLen) {
                result.add(left);
            }
        }
        return result;
    }
}
