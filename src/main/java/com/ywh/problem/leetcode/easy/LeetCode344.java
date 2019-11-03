package com.ywh.problem.leetcode.easy;

/**
 * 反转字符串
 * [字符串] [双指针]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode344 {

    /**
     * 注意区别于二分搜索的循环条件为 l <= h，反转问题的循环条件为 i < j
     *
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public String reverseString(String s) {
        if (s == null) {
            return null;
        }
        char[] c = s.toCharArray();
        for (int i = 0, j = c.length - 1; i < j; i++, j--) {
            char tmp = c[i];
            c[i] = c[j];
            c[j] = tmp;
        }
        return String.valueOf(c);
    }

}
