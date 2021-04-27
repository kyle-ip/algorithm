package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 分割等和子集
 * [动态规划]
 *
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 示例 1：
 *      输入：nums = [1,5,11,5]
 *      输出：true
 *      解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *      输入：nums = [1,2,3,5]
 *      输出：false
 *      解释：数组不能分割成两个元素和相等的子集。
 * 提示：
 *      1 <= nums.length <= 200
 *      1 <= nums[i] <= 100
 *
 * 本题是经典的「NP 完全问题」，也就是说，如果你发现了该问题的一个多项式算法，那么恭喜你证明出了 P=NP，可以期待一下图灵奖了。
 * 正因如此，我们不应期望该问题有多项式时间复杂度的解法。
 * 我们能想到的，例如基于贪心算法的「将数组降序排序后，依次将每个元素添加至当前元素和较小的子集中」之类的方法都是错误的，可以轻松地举出反例。
 * 因此，我们必须尝试非多项式时间复杂度的算法，例如时间复杂度与元素大小相关的动态规划。
 * 
 * @author ywh
 * @since 4/27/2021
 */
public class LeetCode416 {

    /**
     *
     * @param nums
     * @return
     */
    public boolean canPartition0(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }

        // dp[i][j] 表示从数组的 [0,i] 下标范围内选取若干个正整数（可以是 0 个），是否存在一种选取方案使得被选取的正整数的和等于 j。
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }

    /**
     * 动态规划解法
     * Time: O(n*target), Space: O(target)
     *
     * @param nums
     * @return
     */
    public boolean canPartition1(int[] nums) {
        int n = nums.length;

        // 元素个数小于 2，返回 false。
        if (n < 2) {
            return false;
        }

        // 求数组之和、最大值。
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        // 目标是凑成 sum/2，如果和为奇数，或最大值大于目标值，返回 false。
        if (sum % 2 != 0 || maxNum > sum / 2) {
            return false;
        }
        int target = sum / 2;

        // dp[i] 表示能凑成 i。
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; --j) {
                // if (dp[j-num]) dp[j] = dp[j-num]
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }

    /**
     * 记忆化搜索解法
     * Time: O(n^2), Space: O(n)
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        Arrays.sort(nums);
        return dfs(nums, sum / 2, 0, new Boolean[nums.length][sum / 2 + 1]);
    }

    /**
     *
     * @param nums
     * @param target
     * @param i
     * @param memo
     * @return
     */
    private boolean dfs(int[] nums, int target, int i, Boolean[][] memo) {
        // 越界或 nums[i] 大于和的一半。
        if (i >= nums.length || nums[i] > target) {
            return false;
        }
        // 凑出目标值。
        if (nums[i] == target) {
            return true;
        }
        // 从缓存中读取。
        if (memo[i][target] != null) {
            return memo[i][target];
        }
        // 两种情况：当前半个子集取当前值 nums[i] 或不取当前。
        return memo[i][target] = dfs(nums, target - nums[i], i + 1, memo)
            || dfs(nums, target, i + 1, memo);
    }
}
