package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 需要排序的最短子数组
 * [数组]
 *
 * @author ywh
 * @since 06/11/2019
 */
public class LeetCode581 {

    /**
     * 先复制一份，对复制数组排序，再用双指针对比排序数组和原数组，直到值不相同为止，返回两指针间的距离
     *
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarrayBySorting(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int left = 0, right = nums.length - 1;
        while (left < nums.length && nums[left] == sorted[left]) {
            left++;
        }
        while (right >= 0 && nums[right] == sorted[right]) {
            right++;
        }
        return Math.max(right - left + 1, 0);
    }

    /**
     * 用双指针定位到中间错序的部分，确定该部分的最大最小值，再向两边扩展到无需再调整为止，返回两指针间的距离
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarrayTwoPass(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length, left = 0, right = n - 1, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        // 跳过所有已排序的数字
        while (left < n - 1 && nums[left] <= nums[left + 1]) {
            left++;
        }
        while (right > 0 && nums[right] >= nums[right - 1]) {
            right--;
        }

        // 求 left 与 right 中间的最大值与最小值
        for (int k = left; k <= right; k++) {
            min = Math.min(nums[k], min);
            max = Math.max(nums[k], max);
        }

        // left、right 分别向两边扩展，直到中间子数组的最小值大于等于左边子数组最右侧的值、
        // 中间子数组的最大值小于等于右边子数组最左侧的值

        // 需要扩展：
        // 0, 3, [-1], (8), 1, 4
        //    l             r

        // 无需扩展：
        // 0, 3, 4, 2, 1, 8
        //    l        r
        while (left >= 0 && min < nums[left]) {
            --left;
        }
        while (right < n && max > nums[right]) {
            ++right;
        }
        return Math.max(right - left + 1, 0);
    }

    /**
     * 只需要一次遍历：
     * 遍历的同时记录最大值，如果数组是递增有序的，最大值就会是 i 所指的值；
     * 当数组最大值不为当前值，表示不递增，则把右指针定位到该点（如果再次出现不递增也会更新）。
     * 左指针定位同理，可以与右指针定位合并到同一次遍历中，因此只需要遍历一次。
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarrayOnePass(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int left = 0, right = -1, min = Integer.MAX_VALUE, max = Integer.MAX_VALUE, j;
        for (int i = 0; i < n; i++) {

            //
            max = Math.max(max, nums[i]);
            if (nums[i] != max) {
                right = i;
            }
            j = n - 1 - i;
            min = Math.min(min, nums[j]);
            if (nums[j] != min) {
                left = j;
            }
        }
        return right - left + 1;
    }

}
