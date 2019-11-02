package com.ywh.problem.leetcode.medium;

/**
 * 实现平方根函数
 * [数学] [二分搜索]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode69 {

    /**
     * 二分查找法
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
