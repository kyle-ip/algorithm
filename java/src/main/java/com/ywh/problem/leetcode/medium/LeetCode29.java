package com.ywh.problem.leetcode.medium;

/**
 * 两数相除
 * [数学] [二分搜素]
 *
 * @author ywh
 * @since 2020/9/20 22:46
 */
public class LeetCode29 {

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // 被除数和除数是否同号：要作特殊处理（不能用乘法）。
        boolean flag = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        int ret = 0;

        // 先都转为负数。
        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);

        // dividend    divisor    ret
        // -30         -3         0
        // -6                     8
        while (dividend <= divisor) {

            // -3
            int temp = divisor, c = 1;

            // dividend    temp    c    dividend - temp
            // -30         -3      1    -30 - (-3) == -27
            //             -6      2    -30 - (-6) == -24
            //             -12     4    -30 - (-12) == -18
            //             -24     8    -30 - (-24) == -6 > -24, break（取 -6）

            // 减去一个 3、两个 3、四个 3...
            while (dividend - temp <= temp) {
                // -3 * 2 * 2...
                temp = temp << 1;
                // 1 * 2 * 2...
                c = c << 1;
            }
            dividend -= temp;
            ret += c;
        }

        // 同号相除返回正数，否则取相反数。
        return flag ? ret : -ret;
    }
}