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
     * Time: O(n^2), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n;
        }

        // lengths[i] 表示截至 i 的最长序列长度，counts[i] 表示出现该长度的序列的个数。
        int[] lengths = new int[n], counts = new int[n];
        Arrays.fill(counts, 1);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                // 不递增，跳过。
                if (nums[j] >= nums[i]) {
                    continue;
                }
                // 更新 lengths[i]、counts[i]：使得 lengths[i] 始终比 length[j] 长 1，且数量相等。
                if (lengths[j] >= lengths[i]) {
                    lengths[i] = lengths[j] + 1;
                    counts[i] = counts[j];
                }
                // 如果 [0, i] 中递增的长度比 [0, j] 大 1，表示算上 i 后又找到 counts[j] 种最长递增序列的凑法。
                else if (lengths[j] + 1 == lengths[i]) {
                    counts[i] += counts[j];
                }
            }
        }

        // 找到最大值，并在 lengths 中统计该最大值出现的个数，返回。
        int longest = 0, ret = 0;
        for (int length : lengths) {
            longest = Math.max(longest, length);
        }
        for (int i = 0; i < n; ++i) {
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
