package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 最长递增子序列的个数
 * [动态规划]
 *
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * 示例 1:
 *      输入: [1,3,5,4,7]
 *      输出: 2
 *      解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 *      输入: [2,2,2,2,2]
 *      输出: 5
 *      解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 *
 * @author ywh
 * @since 2021/1/6/006
 */
public class LeetCode673 {

    /**
     * TODO: 暂时未理解
     *
     * Time: O(n^2), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int N = nums.length;
        if (N <= 1) {
            return N;
        }

        // 以 nums[i] 结尾的序列，最长序列长度 length[i]，具有该长度的序列的 count[i]。
        // lengths[i] = length of longest ending in nums[i]
        int[] lengths = new int[N];
        // count[i] = number of longest ending in nums[i]
        int[] counts = new int[N];
        Arrays.fill(counts, 1);

        for (int j = 0; j < N; ++j) {
            for (int i = 0; i < j; ++i) {
                if (nums[i] < nums[j]) {
                    if (lengths[i] >= lengths[j]) {
                        lengths[j] = lengths[i] + 1;
                        counts[j] = counts[i];
                    } else if (lengths[i] + 1 == lengths[j]) {
                        counts[j] += counts[i];
                    }
                }
            }
        }

        int longest = 0, ret = 0;
        for (int length : lengths) {
            longest = Math.max(longest, length);
        }
        for (int i = 0; i < N; ++i) {
            if (lengths[i] == longest) {
                ret += counts[i];
            }
        }
        return ret;
    }

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS2(int[] nums) {
        return 0;
    }

}
