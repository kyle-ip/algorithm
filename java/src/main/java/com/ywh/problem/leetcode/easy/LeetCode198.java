package com.ywh.problem.leetcode.easy;

/**
 * 抢劫连排房子
 * [动态规划]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode198 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int robO1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // x1 表示上一次抢劫后的总获利，x0 表示上上一次抢劫后的总获利
        int x0 = 0, x1 = 0, cur;
        for (int num: nums) {
            cur = Math.max(x1, x0 + num);
            x0 = x1;
            x1 = cur;
        }

        return x1;
    }
}
