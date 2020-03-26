package com.ywh.problem.leetcode.easy;

import java.util.Arrays;

/**
 * 变位词校验
 * [字符串] [排序] [哈希表]
 *
 * @author ywh
 * @since 26/03/2020
 */
public class LeetCode242 {


    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramSort(String s, String t) {

        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        char[] sc = s.toCharArray(), tc = t.toCharArray();
        Arrays.sort(sc);
        Arrays.sort(tc);
        for (int i = 0; i < sc.length; i++) {
            if (sc[i] != tc[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramCount(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0) {
                return false;
            }
        }
        return true;
    }

}
