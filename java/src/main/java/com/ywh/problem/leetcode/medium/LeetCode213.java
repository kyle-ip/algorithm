package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.easy.LeetCode198;

/**
 * 抢劫环形房子
 * [动态规划]
 *
 * @author ywh
 * @since 06/11/2019
 */
public class LeetCode213 {

    /**
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int rob(int[] nums, int start, int end) {
        int prev2 = 0, prev1 = 0, cur;
        for (int i = start; i <= end; i++) {
            cur = Math.max(prev2 + nums[i], prev1);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    /**
     * 区别于 {@link LeetCode198} ，头尾的两个房子不能同时抢；
     * 因此需要去头抢一次、去尾墙一次，求两次较大者即可。
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }
}
