package com.ywh.problem.leetcode.medium;

/**
 * 有序数组中求和为给定值的两个数
 * [双指针] [数组] [二分搜索]
 *
 * @author ywh
 * @since 2/13/2019
 */
public class LeetCode167 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] getTwoSumToTarget(int[] nums, int target) {
        int left = 0, right = nums.length - 1, sum;
        while (left < right) {
            sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

}
