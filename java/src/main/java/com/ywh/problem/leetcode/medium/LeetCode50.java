package com.ywh.problem.leetcode.medium;

/**
 * 数值的 n 次方
 * [数学] [二分搜索]
 *
 * @author ywh
 * @since 27/10/2019
 */
public class LeetCode50 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param x
     * @param n
     * @return
     */
    public double pow(double x, int n) {
        double ret = 1;
        for (int i = 0; i < Math.abs(n); i++) {
            ret *= x;
        }
        return n < 0 ? 1 / ret : ret;
    }

    /**
     * TODO 暂时未理解
     *
     * Time: O(log(n)), Space: O(1)
     *
     * @param x
     * @param n
     * @return
     */
    public double powFast(double x, int n) {
        double ret = 1;
        long N = Math.abs((long) n);
        while (N != 0) {
            if ((N & 1) == 1) {
                ret *= x;
            }
            x *= x;
            N >>= 1;
        }
        return n < 0 ? 1 / ret : ret;
    }
}
