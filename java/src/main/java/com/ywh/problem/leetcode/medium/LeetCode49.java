package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 变位词分组
 * [哈希表] [字符串]
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 *      输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 *      输出:
 *      [
 *        ["ate","eat","tea"],
 *        ["nat","tan"],
 *        ["bat"]
 *      ]
 * 说明：
 *      所有输入均为小写字母。
 *      不考虑答案输出的顺序。
 *
 * @author ywh
 * @since 3/15/2019
 */
public class LeetCode49 {

    /**
     * 返回排序的字符串
     * cba => abc
     *
     * @param s
     * @return
     */
    private String getKeyBySort(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    /**
     * 返回以字符出现个数为 key 的字符串（可能存在不同单词相同 key 的情况）。
     * cba => 111
     *
     * @param s
     * @return
     */
    private String getKeyByCount(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'a']++;
        }
        return new String(c);
    }

    /**
     * getKeyByCount：Time: O(n*k)
     * getKeyBySort：Time: O(n*k*log(k)), Space: O(n)
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            // 求 str 的 key（字符及其出现个数，比如 cba => a1b1c1）。
            int[] c = new int[26];
            for (int i = 0; i < str.length(); i++) {
                c[str.charAt(i) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (c[i] != 0) {
                    sb.append((char) (i + 'a')).append(c[i]);
                }
            }
            String key = sb.toString();
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
