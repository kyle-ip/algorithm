package com.ywh.problem.leetcode.easy;

/**
 * Excel 表格的列编号
 * [数学]
 *
 * @author ywh
 * @since 03/01/2020
 */
public class LeetCode171 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param s
     * @return
     */
    public int titleToNumberRight2Left(String s) {
        int base = 1, num = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            num += (s.charAt(i) - 'A' + 1) * base;
            base *= 26;
        }
        return num;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param s
     * @return
     */
    public int titleToNumberLeft2Right(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num = num * 26 + (s.charAt(i) - 'A' + 1);
        }
        return num;
    }
}
