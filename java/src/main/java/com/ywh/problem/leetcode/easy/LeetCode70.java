package com.ywh.problem.leetcode.easy;

/**
 * 爬楼梯
 * [动态规划]
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 示例 1：
 *      输入： 2
 *      输出： 2
 *      解释： 有两种方法可以爬到楼顶。
 *      1. 1 阶 + 1 阶
 *      2. 2 阶
 * 示例 2：
 *      输入： 3
 *      输出： 3
 *      解释： 有三种方法可以爬到楼顶。
 *      1. 1 阶 + 1 阶 + 1 阶
 *      2. 1 阶 + 2 阶
 *      3. 2 阶 + 1 阶
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
        // 要到达 n 可以从 n-1 爬一阶，也可以从 n-2 爬两阶。
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
