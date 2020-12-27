package com.ywh.problem.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 同构字符串
 * [字符串]
 * 
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * 示例 1:
 *      输入: s = "egg", t = "add"
 *      输出: true
 * 示例 2:
 *      输入: s = "foo", t = "bar"
 *      输出: false
 * 示例 3:
 *      输入: s = "paper", t = "title"
 *      输出: true
 * 说明:
 *      你可以假设 s 和 t 具有相同的长度。
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
            if (hash1.get(s.charAt(i)) != hash2.get(t.charAt(i))) {
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
}
