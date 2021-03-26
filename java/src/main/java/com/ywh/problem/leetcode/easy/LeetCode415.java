package com.ywh.problem.leetcode.easy;

/**
 * 字符串相加
 * [字符串]
 *
 * @author ywh
 * @since 02/12/2019
 */
public class LeetCode415 {

    /**
     * Time: O(m+n), Space: O(1)
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings(String num1, String num2) {
        int m = num1.length(), n = num2.length(), k = Math.max(m, n);
        char[] ret = new char[k + 1];
        for (int carry = 0, i = m - 1, j = n - 1; i >= 0 || j >= 0 || carry > 0; carry /= 10) {
            if (i >= 0) {
                carry += num1.charAt(i--) - '0';
            }
            if (j >= 0) {
                carry += num2.charAt(j--) - '0';
            }
            ret[k--] = (char) (carry % 10 + '0');
        }
        return String.valueOf(ret).trim();
    }
}
