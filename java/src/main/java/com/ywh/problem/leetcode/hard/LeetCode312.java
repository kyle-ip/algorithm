package com.ywh.problem.leetcode.hard;

import java.util.Arrays;

/**
 * 戳气球
 * [分治] [动态规划]
 * 
 * 有 n 个气球，编号为 0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1 或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 求所能获得硬币的最大数量。
 * 示例 1：
 *      输入：nums = [3,1,5,8]
 *      输出：167
 *      解释：
 *          nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 *          coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * 示例 2：
 *      输入：nums = [1,5]
 *      输出：10
 *
 * 提示：
 *      n == nums.length
 *      1 <= n <= 500
 *      0 <= nums[i] <= 100
 *
 * @author ywh
 * @since 10/02/2021
 */
public class LeetCode312 {


    /**
     * 记忆化搜索解法：
     * 戳破气球的操作会导致两个气球从不相邻变成相邻，使得后续操作难以处理。因此可以倒过来，将操作视为每次添加一个气球。
     *
     * Time: O(n^3), Space: O(n^2)
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 对数组做处理，将其两边各加上假设存在的 nums[-1] 和 nums[n]，保存在 val 中，可避免越界。
        int[] val = new int[n + 2];
        // 在 [1, n] 中，把 val[i] 的值填充为 nums[i - 1]，两边的值设为 1。即：[1, nums[0], nums[1], nums[2], ..., nums[n-1], 1]。
        System.arraycopy(nums, 0, val, 1, n);
        val[0] = val[n + 1] = 1;

        // 辅助方法 solve 的值，ret[l][r] 表示将 (l, r) 范围内的位置填满气球时能得到的最多硬币数。
        int[][] ret = new int[n + 2][n + 2];

        for (int i = 0; i <= n + 1; i++) {
            Arrays.fill(ret[i], -1);
        }
        return solve(0, n + 1, ret, val);
    }

    /**
     * 表示将 (l, r) 范围内的位置填满气球时能得到的最多硬币数。
     *
     * @param l
     * @param r
     * @param ret
     * @param val
     * @return
     */
    private int solve(int l, int r, int[][] ret, int[] val) {
        // 区间中没有气球，返回 0。
        if (l >= r - 1) {
            return 0;
        }
        // 已填充，不重复计算。
        if (ret[l][r] != -1) {
            return ret[l][r];
        }

        // 枚举区间内所有位置 i：
        for (int i = l + 1; i < r; i++) {
            // i 为当前区间第一个添加的气球，该操作能得到的硬币数为 val[l] * val[i] * val[j]。
            int sum = val[l] * val[i] * val[r];
            // 递归计算分割出的两个区间中对 solve(l, r) 的贡献值。
            sum += solve(l, i, ret, val) + solve(i, r, ret, val);
            // 这三项之和的最大值即为 solve(l, r) 的值。
            ret[l][r] = Math.max(ret[l][r], sum);
        }
        return ret[l][r];
    }

    /**
     * 动态规划解法
     * Time: O(n^3), Space: O(n^2)
     *
     * @param nums
     * @return
     */
    public int maxCoins1(int[] nums) {
        int n = nums.length;
        // dp[l][r] 表示填满开区间 (l, r) 能得到的最多硬币数。边界条件 i >= j - 1 时值为 0。
        int[][] dp = new int[n + 2][n + 2];

        // 对数组做处理，将其两边各加上假设存在的 nums[-1] 和 nums[n]，保存在 val 中，可避免越界。
        int[] val = new int[n + 2];
        System.arraycopy(nums, 0, val, 1, n);
        val[0] = val[n + 1] = 1;

        // 类似记忆化搜索解法，使用动态规划自底向上求解。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][n + 1];
    }
}
