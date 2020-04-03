package com.ywh.problem.leetcode.easy;

/**
 * 移除数组中指定数字
 * [双指针] [数组]
 *
 * @author ywh
 * @since 03/04/2020
 */
public class LeetCode27 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != val) {
                nums[left++] = nums[right];
            }
            right++;
        }
        return left;
    }

}
