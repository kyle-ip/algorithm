package com.ywh.problem.leetcode.medium;

/**
 * 有序数组中查找数字的开始和结束下标
 * [数组] [二分搜索]
 *
 * @author ywh
 * @since 2019/11/29/029
 */
public class LeetCode34 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] findFirstAndLastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int n = nums.length, start = -1, end = -1;
        for (int i = 0; i < n; i++) {
            if (start == -1 && nums[i] == target) {
                start = i;
            }
            if (nums[i] == target) {
                end = i;
            }
        }
        return new int[]{start, end};
    }

    private int binarySearchLastOne(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] binarySearchFirstAndLastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int end = binarySearchLastOne(nums, target);
        int start = binarySearchLastOne(nums, target - 1) + 1;
        if (start >= 0 && start <= end && end < nums.length) {
            return new int[]{start, end};
        } else {
            return new int[]{-1, -1};
        }
    }
}
