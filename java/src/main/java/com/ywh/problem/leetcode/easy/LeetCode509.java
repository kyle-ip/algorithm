package com.ywh.problem.leetcode.easy;

/**
 * 第 n 个斐波那契数
 * [数组] [动态规划]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode509 {

    public int fibRecursive(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    public int fibIterative1(int n) {

        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fibIterative2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int x0 = 0, x1 = 1;
        for (int i = 2; i <= n; i++) {
            int x2 = x0 + x1;
            x0 = x1;
            x1 = x2;
        }
        return x1;
    }

}
