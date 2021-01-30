package com.ywh.problem.leetcode.easy;

/**
 * x 的平方根
 * [数学] [二分搜索]
 * 
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 示例 1:
 *      输入: 4
 *      输出: 2
 * 示例 2:
 *      输入: 8
 *      输出: 2
 *      说明: 8 的平方根是 2.82842...,
 *           由于返回类型是整数，小数部分将被舍去。
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode69 {

    /**
     * 二分搜索法
     * Time: O(log(n)), Space: O(1)
     *
     * @param n
     * @return
     */
    public int sqrtBinarySearch(int n) {
        long low = 0, high = n - 1, mid, pow;
        while (low <= high) {
            mid = low + (high - low) / 2;
            pow = mid * mid;
            if (pow == n) {
                return (int) mid;
            }
            if (pow < n) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) high;
    }

    /**
     * 牛顿法
     * Time: O(log(n)), Space: O(1)
     *
     * @param n
     * @return
     */
    public int sqrtNewton(int n) {
        long x = n;
        while (x * x > n) {
            x = (x + n / x) / 2;
        }
        return (int) x;
    }
}
