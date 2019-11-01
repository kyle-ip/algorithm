package com.ywh.algorithm.leetcode.medium;

/**
 * 二分搜索插入位置
 * [二分搜索]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode35 {

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearchInsertPosition(int[] nums, int target) {
        if (nums.length <= 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        // 注意最终 target 应插入 low 后
        return low;
    }
}
