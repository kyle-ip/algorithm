package com.ywh.problem.leetcode.medium;

/**
 * 旋转有序数组的最小值
 * [数组] [二分搜索]
 *
 * @author ywh
 * @since 26/11/2019
 */
public class LeetCode153 {

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                // 最小值可能正是 mid 的位置，因此不能错过，必须包括在搜索范围内
                high = mid;
            }
        }
        return nums[low];
    }

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int findMinEarlyReturn(int[] nums) {
        int low = 0, high = nums.length - 1;

        // 旋转后的数组只有两种情况：
        // 1. 分两半分别递增，最大值在中间；
        // 2. 全数组递增，最大值在最右端
        while (low < high) {

            // 满足第二种情况，直接返回
            if (nums[low] < nums[high]) {
                return nums[low];
            }
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }

        }
        return nums[low];
    }
}
