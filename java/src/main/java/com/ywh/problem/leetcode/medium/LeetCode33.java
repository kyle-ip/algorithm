package com.ywh.problem.leetcode.medium;

/**
 * 搜索旋转排序数组
 * [数组] [二分搜索]
 *
 * 给你一个整数数组 nums ，和一个整数 target 。
 * 该整数数组原本是按升序排列，但输入时在预先未知的某个点上进行了旋转。（例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] ）。
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1。
 * 示例 1：
 *      输入：nums = [4,5,6,7,0,1,2], target = 0
 *      输出：4
 * 示例 2：
 *      输入：nums = [4,5,6,7,0,1,2], target = 3
 *      输出：-1
 * 示例 3：
 *      输入：nums = [1], target = 0
 *      输出：-1
 * 提示：
 *      1 <= nums.length <= 5000
 *      -10^4 <= nums[i] <= 10^4
 *      nums 中的每个值都独一无二
 *      nums 肯定会在某个点上旋转
 *      -10^4 <= target <= 10^4
 *
 * @author ywh
 * @since 03/11/2019
 */
public class LeetCode33 {

    /**
     * 使用二分搜索前需要区分前半数组是否单调
     *
     * Time: O(log(n)), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInRotatedSortedArray(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 0 ~ mid 递增，如 3, 4, 5, [6], 7, 1, 2
            if (nums[mid] >= nums[low]) {
                // target 在 low 和 mid 之间，如 3, 4, {5}, [6], 7, 1, 2，则 => 3, 4, 5
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // 0 ~ mid 不递增，如 6, 7, 1, [2], 3, 4, 5
            else {
                // target 在 mid 和 high 之间，如 6, 7, 1, [2], {3}, 4, 5，则 => 3, 4, 5
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
