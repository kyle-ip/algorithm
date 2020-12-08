package com.ywh.problem.leetcode.medium;

/**
 * 预测赢家
 * [极小化极大] [动态规划]
 *
 * @author ywh
 * @since 07/12/2019
 */
public class LeetCode486 {

    /**
     * 动态规划解法
     * Time: O(n^2), Space: O(n^2)
     *
     * @param nums
     * @return
     */
    public static boolean predictTheWinnerDp(int[] nums) {

        // dp[i][j] 表示当前剩下的数范围是 i ~ j 时，当前选手（不一定为先手）与另一名选手的分数差。
        int[][] dp = new int[nums.length][nums.length];

        // 只剩下一个数，分差即为该值。
        for (int i = 0; i < nums.length; i++) {
            dp[i][i] = nums[i];
        }
        for (int s = nums.length - 2; s >= 0; s--) {
            for (int e = s + 1; e < nums.length; e++) {
                //             |<------ a ------>|
                // [ ] ... [s] [s+1] ... [e-1] [e] ... [ ]
                //         |<------ b ------>|
                // a 表示当前选手取得 s 的值后，与另一名选手的差值（从左边取得 s 后，剩余区间为 [s+1, e]）；b 表示从另一端取，因此保存两种取法的较大者即可。
                int a = nums[s] - dp[s + 1][e], b = nums[e] - dp[s][e - 1];
                dp[s][e] = Math.max(a, b);
            }
        }

        // 在 [0, nums.length-1] 中，当前选手与另一名选手的分数差大于等于 0，表示当前选手有可能赢。
        return dp[0][nums.length - 1] >= 0;
    }

    /**
     * 动态规划解法
     * Time: O(n^2), Space: O(n)
     *
     * @param nums
     * @return
     */
    public static boolean predictTheWinnerDp2(int[] nums) {
        // 与上面的解法同理，由于 dp[i][j] 的值只和 dp[i+1][j] 与 dp[i][j-1] 有关，所以可以压缩成一维。
        // dp[e] 表示剩下的数范围是 [0, e] 时，当前选手（不一定为先手）与另一名选手的分数差。
        int[] dp = new int[nums.length];
        System.arraycopy(nums, 0, dp, 0, nums.length);
        for (int s = nums.length - 2; s >= 0; s--) {
            for (int e = s + 1; e < nums.length; e++) {
                dp[e] = Math.max(nums[s] - dp[e], nums[e] - dp[e - 1]);
            }
        }
        return dp[nums.length - 1] >= 0;
    }

    /**
     * 递归解法
     * Time: O(2^n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public static boolean predictTheWinnerRecursive(int[] nums) {
        // 返回最大得分，正负号表示该得分的获得者（1 为先手，-1 为后手）。
        return winner(nums, 0, nums.length - 1, 1) >= 0;
    }

    /**
     * 尽可能取较大的得分
     *
     * @param nums
     * @param start 剩余数组的起始下标
     * @param end   剩余数组的结束下标
     * @param turn  下一个取数的玩家
     * @return
     */
    private static int winner(int[] nums, int start, int end, int turn) {
        // 最终只剩下一个数，返回（注意该数属于当前玩家）。
        if (start == end) {
            return turn * nums[start];
        }
        // 先手会选择取左和取右中的较大者，递归处理两种情况。
        int left = turn * nums[start] + winner(nums, start + 1, end, -turn);
        int right = turn * nums[end] + winner(nums, start, end - 1, -turn);
        return turn * Math.max(turn * left, turn * right);
    }
}
