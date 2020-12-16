package com.ywh.problem.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 单词规律
 * [哈希表]
 *
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * 示例1:
 *      输入: pattern = "abba", str = "dog cat cat dog"
 *      输出: true
 * 示例 2:
 *      输入:pattern = "abba", str = "dog cat cat fish"
 *      输出: false
 * 示例 3:
 *      输入: pattern = "aaaa", str = "dog cat cat dog"
 *      输出: false
 * 示例 4:
 *      输入: pattern = "abba", str = "dog dog dog dog"
 *      输出: false
 * 说明:
 *      你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
 *
 * @author ywh
 * @since 2020/12/16/016
 */
public class LeetCode290 {

    /**
     * Time: O(n), Space: (n)
     *
     * @param pattern
     * @param s
     * @return
     */
    public static boolean wordPattern(String pattern, String s) {
        String[] strArr = s.split(" ");
        char[] charArr = pattern.toCharArray();
        if (strArr.length != charArr.length) {
            return false;
        }

        Map<String, Character> str2Char = new HashMap<>();
        Map<Character, String> char2Str = new HashMap<>();
        for (int i = 0; i < charArr.length; i++) {
            // 两个 map 为空。
            if (!str2Char.containsKey(strArr[i]) && !char2Str.containsKey(charArr[i])) {
                str2Char.put(strArr[i], charArr[i]);
                char2Str.put(charArr[i], strArr[i]);
            }
            // 其中一个 map 为空。
            else if (!str2Char.containsKey(strArr[i]) || !char2Str.containsKey(charArr[i])) {
                return false;
            }
            // 两个 map 都不为空，且值对不上。
            else if (str2Char.get(strArr[i]) != charArr[i] || !char2Str.get(charArr[i]).equals(strArr[i])) {
                return false;
            }
        }
        return true;
    }
}
