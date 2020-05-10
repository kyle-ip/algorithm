package com.ywh.problem.leetcode.easy;

/**
 * 非减数组
 * [数组]
 *
 * @author ywh
 * @since 10/05/2020
 */
public class LeetCode665 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public boolean checkPossibilityBoolean(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        boolean modified = nums[0] > nums[1];
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] <= nums[i + 1]) {
                continue;
            }
            if (modified) {
                return false;
            }
            // 3, [5], 4 => 3, 3, 4
            if (nums[i + 1] >= nums[i - 1]) {
                nums[i] = nums[i - 1];
            }
            // 4, [5], 3 => 4, 5, 5
            else {
                nums[i + 1] = nums[i];
            }
            modified = true;
        }
        return true;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public boolean checkPossibilityInt(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        int modified = nums[0] > nums[1] ? 1 : 0;
        for (int i = 1; i < nums.length - 1 && modified <= 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                ++modified;
                if (nums[i + 1] >= nums[i - 1]) {
                    nums[i] = nums[i - 1];
                } else {
                    nums[i + 1] = nums[i];
                }
            }
        }
        return modified <= 1;
    }
}
