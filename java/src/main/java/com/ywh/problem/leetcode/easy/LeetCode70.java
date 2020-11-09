package com.ywh.problem.leetcode.easy;

/**
 * 爬楼梯方法数
 * [动态规划]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode70 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param n
     * @return
     */
    public int climbStairsRecursive(int n) {
        if (n < 2) {
            return 1;
        }
        // 到达 n，可以从 n - 1 爬一阶，也可以从 n - 2 爬两阶。
        return climbStairsRecursive(n - 1) + climbStairsRecursive(n - 2);
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param n
     * @return
     */
    public int climbStairsIterative(int n) {
        int x0 = 1, x1 = 1, x2;
        for (int i = 1; i < n; i++) {
            x2 = x0 + x1;
            x0 = x1;
            x1 = x2;
        }
        return x1;
    }

}
