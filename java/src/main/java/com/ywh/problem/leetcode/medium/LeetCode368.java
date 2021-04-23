package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最大整除子集
 * 
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，
 * 子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 *      answer[i] % answer[j] == 0 ，或
 *      answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * 示例 1：
 *      输入：nums = [1,2,3]
 *      输出：[1,2]
 *      解释：[1,3] 也会被视为正确答案。
 * 示例 2：
 *      输入：nums = [1,2,4,8]
 *      输出：[1,2,4,8]
 * 提示：
 *      1 <= nums.length <= 1000
 *      1 <= nums[i] <= 2 * 109
 *      nums 中的所有整数 互不相同
 *
 * @author ywh
 * @since 4/23/2021
 */
public class LeetCode368 {

    /**
     * 动态规划解法
     *
     * 整除子集：
     * 一个所有元素互不相同的集合中的任意元素存在整除关系，就称为整除子集。
     *
     * Time: O(n^2), Space: O(n)
     *
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        // dp[i] 表示（nums 升序排列），以 nums[i] 为最大整数的「整除子集」的大小（因此 nums[i] 必须被选择）。

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        // 动态规划过程找出最大子集的个数、最大子集中的最大整数。
        int maxSize = 1, maxVal = dp[0];
        for (int i = 1; i < n; i++) {
            // 枚举 [0, i)，如果其中存在 nums[i] % nums[j] == 0，表示 nums[i] 可以扩充在以 nums[j] 为最大整数的整除子集里成为一个更大的整除子集。
            for (int j = 0; j < i; j++) {
                // 注意题设没有重复元素。
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }

        // 最大长度为 1，返回第一个元素即可。
        List<Integer> ret = new ArrayList<>();
        if (maxSize == 1) {
            ret.add(nums[0]);
            return ret;
        }

        // 倒推获得最大子集：
        // 1. 倒序遍历 dp，直到找到 dp[i] = maxSize 为止，把此时对应的 nums[i] 加入结果集，此时 maxVal = nums[i]。
        // 2. 然后将 maxSize 值 -1，继续倒序遍历找到 dp[i] == maxSize，且 nums[i] 能整除 maxVal 的 i 为止，
        //      将此时的 nums[i] 加入结果集，maxVal 更新为此时的 nums[i]。
        // 3. 重复上述操作，直到 maxSize 的值变成 0，此时的结果集即为一个目标子集。
        for (int i = n - 1; i >= 0 && maxSize > 0; i--) {
            if (maxSize == dp[i] && maxVal % nums[i] == 0) {
                ret.add(nums[i]);
                maxVal = nums[i];
                maxSize--;
            }
        }
        return ret;
    }
}
