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
        int i = num1.length() - 1, j = num1.length() - 1, k = Math.max(num1.length(), num2.length()), carry = 0;
        char[] ret = new char[k + 1];
        while (i >= 0 || j >= 0 || carry > 0) {
            if (i >= 0) {
                carry += num1.charAt(i--) - '0';
            }
            if (j >= 0) {
                carry += num2.charAt(j--) - '0';
            }
            ret[k--] = (char) (carry % 10 + '0');
            carry /= 10;
        }
        return String.valueOf(ret).trim();
    }
}
