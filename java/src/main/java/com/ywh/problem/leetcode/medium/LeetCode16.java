package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 求和最接近目标值的三个数
 * [双指针] [数组]
 *
 * @author ywh
 * @since 01/12/2019
 */
public class LeetCode16 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int result = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int k = nums.length - 1; k >= 2; k--) {
            int left = 0, right = k - 1;

            // 由于不能像 3sum、4sum 那样只考虑完全相等的组合（从而直接跳过不符合的值），而是要找到最接近，因此必须一步步移动比对
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[k];
                if (sum == target) {
                    return target;
                }
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
                if (Math.abs(target - sum) < Math.abs(target - result)) {
                    result = sum;
                }
            }
        }
        return result;
    }

}
