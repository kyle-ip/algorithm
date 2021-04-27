package com.ywh.problem.leetcode.easy;

/**
 * 斐波那契数
 * [数组] [动态规划]
 * 
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *      F(0) = 0，F(1) = 1
 *      F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 * 示例 1：
 *      输入：2
 *      输出：1
 *      解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * 示例 2：
 *      输入：3
 *      输出：2
 *      解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * 示例 3：
 *      输入：4
 *      输出：3
 *      解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 * 提示：
 *      0 <= n <= 30
 * 
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode509 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param n
     * @return
     */
    public int fibRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param n
     * @return
     */
    public int fibIterative1(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param n
     * @return
     */
    public int fibIterative2(int n) {
        if (n <= 1) {
            return n;
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
