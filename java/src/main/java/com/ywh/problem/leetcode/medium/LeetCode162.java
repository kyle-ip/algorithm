package com.ywh.problem.leetcode.medium;

/**
 * 查找数组的波峰
 * [数组] [二分搜索]
 *
 * @author ywh
 * @since 18/12/2019
 */
public class LeetCode162 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int findPeakElementSequentialSearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            // 开始出现递减，直接返回下标
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }

        // 数组一直递增，返回最后一个下标
        return nums.length - 1;
    }

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int findPeakElementBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1, left, right;
        while (low < high) {
            int mid = low + (high - low) / 2;

            // 取 mid 左边和右边的值，如果 mid 落在边界上，则把它的左/右边的值设为最小值
            left = mid-1 >= 0 ? nums[mid-1] : Integer.MIN_VALUE;
            right = mid+1 < nums.length ? nums[mid+1] : Integer.MIN_VALUE;

            // 如果 mid 比左右的值都大，直接返回
            if (nums[mid] > left && nums[mid] > right) {
                return mid;
            }
            // 如果 mid 左边的值比它大，则说明波峰在左边，否则在右边
            if (left > nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}
