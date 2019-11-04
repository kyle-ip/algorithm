package com.ywh.problem.leetcode.medium;

/**
 * 旋转有序数组的搜索
 * [数组] [二分搜索]
 *
 * @author ywh
 * @since 03/11/2019
 */
public class LeetCode33 {

    /**
     * 使用二分搜索前需要区分前半数组是否单调
     *
     * Time: O(log(n)), Space: O(1)
     *
      * @param nums
     * @param target
     * @return
     */
    public int searchInRotatedSortedArray(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 0 ~ mid 递增，如 3, 4, 5, [6], 7, 1, 2
            if (nums[mid] >= nums[low]) {
                // target 在 low 和 mid 之间，如 3, 4, {5}, [6], 7, 1, 2，则 => 3, 4, 5
                if (nums[mid] > target && nums[low] <= target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // 0 ~ mid 不递增，如 6, 7, 1, [2], 3, 4, 5
            else {
                // target 在 mid 和 high 之间，如 6, 7, 1, [2], {3}, 4, 5，则 => 3, 4, 5
                if (nums[mid] < target && nums[high] >= target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
