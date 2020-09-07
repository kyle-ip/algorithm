package com.ywh.problem.leetcode.easy;

/**
 * Excel 表格的列标题
 * [数学]
 *
 * @author ywh
 * @since 18/02/2020
 */
public class LeetCode168 {

    /**
     * Time: O(log26(n)), Space: O(log26(n))
     *
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n -= 1;
            char c = (char) (n % 26 + 'A');
            sb.append(c);
            n /= 26;
        }
        return sb.reverse().toString();
    }

}
