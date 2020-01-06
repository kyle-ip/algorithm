package com.ywh.problem.leetcode.easy;

/**
 * 最后一个单词的长度
 * [字符串]
 *
 * @author ywh
 * @since 06/01/2020
 */
public class LeetCode58 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1, length = 0;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        if (end < 0) {
            return 0;
        }
        while (end >= 0 && s.charAt(end) != ' ') {
            end--;
            length++;
        }
        return length;
    }
}
