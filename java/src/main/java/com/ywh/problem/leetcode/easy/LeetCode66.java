package com.ywh.problem.leetcode.easy;

/**
 * 加一
 * [数组]
 * 
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 示例 1：
 *      输入：digits = [1,2,3]
 *      输出：[1,2,4]
 *      解释：输入数组表示数字 123。
 * 示例 2：
 *      输入：digits = [4,3,2,1]
 *      输出：[4,3,2,2]
 *      解释：输入数组表示数字 4321。
 * 示例 3：
 *      输入：digits = [0]
 *      输出：[1]
 * 提示：
 *      1 <= digits.length <= 100
 *      0 <= digits[i] <= 9
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

        // 从最低位（最右）开始向左移动，途径所有 9 都置为 0，直到没有 9。
        int p = digits.length - 1;
        for (; p >= 0 && digits[p] == 9; digits[p--] = 0);

        // 越出最左表示进位，返回新数组。
        if (p == -1) {
            int[] ret = new int[digits.length + 1];
            ret[0] = 1;
            return ret;
        }

        // 否则在从右数起、第一个不为 9 的数字 +1，返回原数组。
        digits[p]++;
        return digits;
    }

}
