package com.ywh.algorithm.leetcode.medium;

import java.util.*;

/**
 * 变位词分组
 * [哈希表] [字符串]
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
     * 返回以字符出现个数为 key 的字符串（可能存在不同单词相同 key 的情况）
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
     * 返回以字符及其出现个数为 key 的字符串
     * cba => a1b1c1
     *
     * @param s
     * @return
     */
    private String getKeyByCount2(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'a']++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (c[i] != 0) {
                stringBuilder.append((char) (i + 'a'))
                    .append(c[i]);
            }
        }
        return stringBuilder.toString();
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
        List<List<String>> result = new ArrayList<>();

        for (String str: strs) {
            String key = getKeyByCount2(str);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return result;
    }

}
