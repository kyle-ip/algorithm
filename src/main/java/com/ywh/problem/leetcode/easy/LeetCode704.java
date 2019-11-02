package com.ywh.problem.leetcode.easy;

import java.util.Stack;

/**
 * 二分搜索
 * [二分搜索]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode704 {

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


}
