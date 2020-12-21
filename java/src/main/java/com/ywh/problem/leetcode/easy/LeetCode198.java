package com.ywh.problem.leetcode.easy;

/**
 * 打家劫舍
 * [动态规划]
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 示例 1：
 *      输入：[1,2,3,1]
 *      输出：4
 *      解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *           偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *      输入：[2,7,9,3,1]
 *      输出：12
 *      解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *           偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 提示：
 *      0 <= nums.length <= 100
 *      0 <= nums[i] <= 400
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode198 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int robO1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // x1 表示上一次抢劫后的总获利，x0 表示上上一次抢劫后的总获利，sum 表示截至当前元素的总获利。
        int x0 = 0, x1 = 0, sum = 0;
        for (int num: nums) {
            // 当 num == 3：
            //      +-------------->    5   √
            //      |
            // 1    5    3
            // |         |
            // +---------+--------->    4
            sum = Math.max(x1, x0 + num);
            x0 = x1;
            x1 = sum;
        }
        return sum;
    }
}
