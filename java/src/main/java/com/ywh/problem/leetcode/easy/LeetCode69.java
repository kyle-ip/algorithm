package com.ywh.problem.leetcode.easy;

/**
 * x 的平方根
 * [数学] [二分查找]
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
     * 二分查找
     * 注意上界是 x！！！！
     *
     * Time: O(log(n)), Space: O(1)
     *
     * @param x
     * @return
     */
    public int sqrtBinarySearch(int x) {
        long low = 0, high = x;
        while (low <= high) {
            long mid = low + (high - low) / 2, pow = mid * mid;
            if (pow == x) {
                return (int) mid;
            }
            if (pow < x) {
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
     * @param x
     * @return
     */
    public int sqrtNewton(int x) {
        long i = x;
        while (i * i > x) {
            i = (i + x / i) / 2;
        }
        return (int) i;
    }
}
