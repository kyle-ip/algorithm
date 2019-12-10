package com.ywh.problem.leetcode.medium;

/**
 * 预测赢家
 * [极小化极大] [动态规划]
 * TODO 暂时未理解
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

        // dp[i][j] 表示当前剩下的数范围是 i ~ j 时，当前选手（不一定为先手）与另一名选手的分数差
        int[][] dp = new int[nums.length + 1][nums.length];
        for (int start = nums.length; start >= 0; start--) {
            for (int end = start + 1; end < nums.length; end++) {
                int a = nums[start] - dp[start + 1][end];
                int b = nums[end] - dp[start][end - 1];
                dp[start][end] = Math.max(a, b);
            }
        }
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

        // dp[i][j] 表示当前剩下的数范围是 i ~ j 时，当前选手（不一定为先手）与另一名选手的分数差
        int[] dp = new int[nums.length];
        for (int start = nums.length; start >= 0; start--) {
            for (int end = start + 1; end < nums.length; end++) {
                int a = nums[start] - dp[end];
                int b = nums[end] - dp[end - 1];
                dp[end] = Math.max(a, b);
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
        // 返回最大得分，正负号表示该得分的获得者（1 为先手，-1 为后手）
        return winner(nums, 0, nums.length - 1, 1) >= 0;
    }

    /**
     * 尽可能取较大的得分
     *
     * @param nums
     * @param start     剩余数组的起始下标
     * @param end       剩余数组的结束下标
     * @param turn      下一个取数的玩家
     * @return
     */
    private static int winner(int[] nums, int start, int end, int turn) {

        // 最终只剩下一个数，返回（注意该数属于当前玩家）
        if (start == end) {
            return turn * nums[start];
        }
        // 先手会选择取左和取右中的较大者，递归处理两种情况
        int left = turn * nums[start] + winner(nums, start + 1, end, -turn);
        int right = turn * nums[end] + winner(nums, start, end - 1, -turn);
        return turn * Math.max(turn * left, turn * right);
    }

    public static void main(String[] args) {
        System.out.println(predictTheWinnerRecursive(new int[]{1, 5, 2}));
        System.out.println(predictTheWinnerRecursive(new int[]{1, 5, 233, 7}));
    }
}
