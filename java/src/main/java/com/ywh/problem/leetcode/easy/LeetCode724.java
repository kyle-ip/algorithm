package com.ywh.problem.leetcode.easy;

/**
 * 寻找数组的平衡点
 * [数组]
 *
 * @author ywh
 * @since 01/05/2020
 */
public class LeetCode724 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        // preSum[i] 表示前 i 个元素之和（即 0 ~ i - 1）
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[n - 1];
        }
        for (int i = 0; i < n; i++) {
            if (preSum[i] == preSum[n] - preSum[i  + 1]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int pivotIndexO1Space(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // sum 表示从 0 到 i 的元素之和，total 为所有元素之和
        int sum = 0, total = 0;
        for (int num : nums) {
            total += num;
        }
        for (int i = 0; i < nums.length; i++) {
            // i == 5
            // total: s(0 ~ 9)
            // sum: s(0 ~ 4)
            // total - sum - nums[5]: s(6 ~ 9)
            if (total - sum - nums[i] == sum) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}