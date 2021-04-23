package com.ywh.problem.leetcode.easy;

/**
 * 搜索插入位置
 * [二分查找]
 * 
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * 示例 1:
 *      输入: [1,3,5,6], 5
 *      输出: 2
 * 示例 2:
 *      输入: [1,3,5,6], 2
 *      输出: 1
 * 示例 3:
 *      输入: [1,3,5,6], 7
 *      输出: 4
 * 示例 4:
 *      输入: [1,3,5,6], 0
 *      输出: 0
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

        // 注意最终 low > high，target 应插入 low 的位置（前）。
        return low;
    }
}
