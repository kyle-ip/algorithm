package com.ywh.problem.leetcode.easy;

/**
 * 反转整数
 * [数学]
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
            // 在 y 更新前判断是否溢出，则不需要 long
            if (y < Integer.MIN_VALUE / 10 || y > Integer.MAX_VALUE / 10) {
                return 0;
            }

            // 注意某些语言需要对负号做特殊处理，比如 Python
            y = y * 10 + x % 10;
            x /= 10;
        }
        return y;
    }

}
