package com.ywh.problem.leetcode.easy;

/**
 * 整数反转
 * [数学]
 *
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1：
 *      输入: 123
 *      输出: 321
 * 示例 2：
 *      输入: -123
 *      输出: -321
 * 示例 3：
 *      输入: 120
 *      输出: 21
 * 注意：
 *      假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31, 2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @author ywh
 * @since 09/11/2019
 */
public class LeetCode7 {

    /**
     * Time: O(k), Space: O(1)
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int y = 0;
        while (x != 0) {
            // 在 y 更新前判断是否溢出，则不需要 long。
            if (y < Integer.MIN_VALUE / 10 || y > Integer.MAX_VALUE / 10) {
                return 0;
            }
            // 注意某些语言需要对负号做特殊处理，比如 Python。
            y = y * 10 + x % 10;
            x /= 10;
        }
        return y;
    }

}
