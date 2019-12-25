package com.ywh.problem.leetcode.easy;

/**
 * 数组加一
 * [数组]
 *
 * @author ywh
 * @since 25/12/2019
 */
public class LeetCode66 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {

        // 从最低位（最右）开始向左移动，所有 9 都置为 0
        int p = digits.length - 1;
        while (p >= 0 && digits[p] == 9) {
            digits[p--] = 0;
        }
        // 如果已经越出最左，表示进位，返回新数组
        if (p == -1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }
        // 否则在从右数起、第一个不为 9 的数字 + 1，返回原数组
        else {
            digits[p] += 1;
            return digits;
        }
    }

}
