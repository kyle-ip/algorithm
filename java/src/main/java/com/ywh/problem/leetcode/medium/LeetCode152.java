package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.easy.LeetCode53;

/**
 * 连续子序列的最大积
 * [数组] [动态规划]
 *
 * @author ywh
 * @since 08/11/2019
 */
public class LeetCode152 {

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * 参考 {@link LeetCode53}
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int maxProductOfSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 记录全局最大乘积、当前最大乘积、当前最小乘积（数组存在负数，可以让当前最大/小值变成最小/大，所以每轮乘积都要比较两者）。
        int ret = nums[0], curMax = nums[0], curMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int a = curMax * nums[i], b = curMin * nums[i];
            // 因为要求连续，当前最大/小值还要和当前元素比较：如果不如当前元素 nums[i] 大/小，则取当前元素（表示重新开始算）。
            curMax = max(a, b, nums[i]);
            curMin = min(a, b, nums[i]);

            // 全局最大值则每次与当前最大值比较，取较大者，最后返回即可。
            ret = Math.max(ret, curMax);
        }
        return ret;
    }
}
