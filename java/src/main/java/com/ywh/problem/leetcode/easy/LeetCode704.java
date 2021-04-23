package com.ywh.problem.leetcode.easy;

/**
 * 二分查找
 * [二分查找]
 * 
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * 示例 1:
 *      输入: nums = [-1,0,3,5,9,12], target = 9
 *      输出: 4
 *      解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 *
 *      输入: nums = [-1,0,3,5,9,12], target = 2
 *      输出: -1
 *      解释: 2 不存在 nums 中因此返回 -1
 * 提示：
 *      你可以假设 nums 中的所有元素是不重复的。
 *      n 将在 [1, 10000]之间。
 *      nums 的每个元素都将在 [-9999, 9999]之间。
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
