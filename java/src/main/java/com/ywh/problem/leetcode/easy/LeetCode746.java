package com.ywh.problem.leetcode.easy;

/**
 * 爬楼梯的最小代价
 * [数组] [动态规划]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode746 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs1(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }

        int length = cost.length;
        int[] dp = new int[length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[length - 1], dp[length - 2]);
    }


    /**
     * Time: O(n), Space: O(1)
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs2(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }
        int x0 = cost[0], x1 = cost[1], cur;
        for (int i = 2; i < cost.length; i++) {
            cur = Math.min(x0, x1) + cost[i];
            x0 = x1;
            x1 = cur;
        }
        return Math.min(x0, x1);
    }
}
