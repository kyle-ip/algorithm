package com.ywh.problem.leetcode.easy;

/**
 * 字符串相加
 * [字符串]
 * 
 * 给定两个字符串形式的非负整数 num1 和 num2 ，计算它们的和。
 * 提示：
 *      num1 和num2 的长度都小于 5100
 *      num1 和num2 都只包含数字 0-9
 *      num1 和num2 都不包含任何前导零
 *      你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
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
        for (int carry = 0, i = m - 1, j = n - 1; i >= 0 || j >= 0 || carry > 0; carry /= 10, k--) {
            carry += i >= 0? num1.charAt(i--) - '0': 0;
            carry += j >= 0? num2.charAt(j--) - '0': 0;
            ret[k] = (char) (carry % 10 + '0');
        }
        return String.valueOf(ret).trim();
    }
}
