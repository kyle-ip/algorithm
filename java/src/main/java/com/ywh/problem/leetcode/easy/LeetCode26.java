package com.ywh.problem.leetcode.easy;

/**
 * 移除有序数组中的重复元素
 * [双指针] [数组]
 *
 * @author ywh
 * @since 06/11/2019
 */
public class LeetCode26 {

    /**
     * 左指针定位在出现重复元素的位置（同时也是不含重复元素的子数组长度），右指针不断向后移动；
     * 当右指针发现不同元素，则把值赋予左指针的位置，左指针后移；
     * 右指针到达末尾，返回左指针即可
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 1, right = 1;
        while (right < nums.length) {
            if (nums[right] == nums[right - 1]) {
                nums[left++] = nums[right];
            }
            right++;
        }
        return left;
    }

}
