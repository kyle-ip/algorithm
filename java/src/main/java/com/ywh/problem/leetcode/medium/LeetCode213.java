package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.easy.LeetCode198;

/**
 * 打家劫舍 II
 * [动态规划]
 * 
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈，
 * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * 示例 1：
 *      输入：nums = [2,3,2]
 *      输出：3
 *      解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 *      输入：nums = [1,2,3,1]
 *      输出：4
 *      解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *           偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 *      输入：nums = [0]
 *      输出：0
 * 提示：
 *      1 <= nums.length <= 100
 *      0 <= nums[i] <= 1000
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
        int x0 = 0, x1 = 0;
        for (int i = start; i <= end; i++) {
            int cur = Math.max(x1, x0 + nums[i]);
            x0 = x1;
            x1 = cur;
        }
        return x1;
    }

    /**
     * 区别于 {@link LeetCode198} ，头尾的两个房子不能同时抢；
     * 因此需要去头抢一次、去尾抢一次，求两次较大者即可。
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }
}
