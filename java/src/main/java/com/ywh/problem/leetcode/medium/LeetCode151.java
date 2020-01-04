package com.ywh.problem.leetcode.medium;

/**
 * 翻转单词顺序
 * [字符串]
 *
 * @author ywh
 * @since 04/01/2020
 */
public class LeetCode151 {

    /**
     * 翻转从字符串 i 到 j 的部分
     *
     * @param str
     * @param i
     * @param j
     */
    private void reverse(char[] str, int i, int j) {
        for (; i < j; i++, j--) {
            char tmp = str[i];
            str[i] = str[j];
            str[j] = tmp;
        }
    }

    /**
     * 先逐个翻转所有单词，再翻转整个字符串
     * 处理空格：翻转后逐个单词向前拷贝
     *
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int p = 0, q = 0, end = str.length - 1;
        while (end >= 0 && str[end] == ' ') {
            --end;
        }
        while (q <= end) {
            // 取换位后单词应插入的开始位置（可能是空格）
            int start = p;

            // 跳过空格，取单词的开始位置
            while (q <= end && str[q] == ' ') {
                ++q;
            }

            // 复制一个单词，并翻转它
            while (q <= end && str[q] != ' ') {
                str[p++] = str[q++];
            }
            reverse(str, start, p-1);

            // 单词间补上空格
            if (q <= end) {
                str[p++] = ' ';
            }
        }
        // 翻转整个字符串
        reverse(str, 0, p-1);
        return new String(str, 0, p);
    }
}
