package com.ywh.problem.leetcode.medium;

/**
 * 组合总和 Ⅳ
 * [动态规划]
 * 
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 * 示例 1：
 *      输入：nums = [1,2,3], target = 4
 *      输出：7
 *      解释：
 *          所有可能的组合为：
 *          (1, 1, 1, 1)
 *          (1, 1, 2)
 *          (1, 2, 1)
 *          (1, 3)
 *          (2, 1, 1)
 *          (2, 2)
 *          (3, 1)
 *      请注意，顺序不同的序列被视作不同的组合。
 * 示例 2：
 *      输入：nums = [9], target = 3
 *      输出：0
 * 提示：
 *      1 <= nums.length <= 200
 *      1 <= nums[i] <= 1000
 *      nums 中的所有元素 互不相同
 *      1 <= target <= 1000
 * 
 * @author ywh
 * @since 4/26/2021
 */
public class LeetCode377 {

    /**
     * Time: O(k*n), Space: O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    public int combinationSum4(int[] nums, int k) {

        // dp[i] 表示选取的元素之和等于 i 的方案数。
        int[] dp = new int[k + 1];
        dp[0] = 1;
        // 枚举 [1, k]，遍历 nums 凑出每个值。
        for (int i = 1; i <= k; i++) {
            for (int num : nums) {
                // 当 num<= i，可假设要凑出 i，使用的最后一个元素为 num。
                // 比如要凑出 3，此时枚举到 2，dp[3-2] == d[1]，则表示凑出 3 的方案数等于凑出 1 的方案数加上其他方案（的个数），
                // 因为此时 num==2 可证明 dp[1] 是可行的。
                dp[i] += i >= num? dp[i - num]: 0;
            }
        }
        return dp[k];
    }
}
