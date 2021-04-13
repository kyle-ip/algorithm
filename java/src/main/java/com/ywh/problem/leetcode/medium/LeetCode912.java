package com.ywh.problem.leetcode.medium;

/**
 * 排序数组
 * [排序] [数组]
 * 
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * 示例 1：
 *      输入：nums = [5,2,3,1]
 *      输出：[1,2,3,5]
 * 示例 2：
 *      输入：nums = [5,1,1,2,0,0]
 *      输出：[0,0,1,1,2,5]
 * 提示：
 *      1 <= nums.length <= 50000
 *      -50000 <= nums[i] <= 50000
 *
 * @author ywh
 * @since 4/13/2021
 */
public class LeetCode912 {

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void hoareSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int i = low, j = high, pivot = nums[i + (j - i) / 2];
        for (;;) {
            for (; nums[i] < pivot; i++) {}
            for (; nums[j] > pivot; j--) {}
            if (i >= j) {
                break;
            }
            swap(nums, i++, j--);
        }
        hoareSort(nums, low, j);
        hoareSort(nums, j + 1, high);
    }

    public int[] sortArray(int[] nums) {
        hoareSort(nums, 0, nums.length - 1);
        return nums;
    }

}
