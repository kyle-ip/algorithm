package com.ywh.algorithm.leetcode.easy;

/**
 * 反转单词
 * [字符串]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode557 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] c = s.toCharArray();

        // start、end 分别定位到每个单词的起始和结束（end 定位单词后的空格，每轮翻转都把 end 与 start 置于空格后第一位）
        int start = 0, end = 0;
        while (start < c.length) {
            while (end < c.length && c[end] != ' '){
                end++;
            }

            // 与单指针的写法相比更推荐这种
            for (int i = start, j = end - 1; i < j; i++, j--) {
                char tmp = c[i];
                c[i] = c[j];
                c[j] = tmp;
            }

            // 跳过空格
            start = end + 1;
            end = start;
        }
        return new String(c);
    }

}
