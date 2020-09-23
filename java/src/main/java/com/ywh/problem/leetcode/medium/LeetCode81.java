package com.ywh.problem.leetcode.medium;

/**
 * 搜索旋转排序数组 II
 *
 * @author ywh
 * @since 2020/9/23/023
 */
public class LeetCode81 {

    /**
     * 参考 {@link LeetCode33}，需要特殊处理相同元素。
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            }
            // 前半部分都是重复值，向右移动。
            if (nums[low] == nums[mid]) {
                low++;
                continue;
            }
            // 0 ~ mid 递增：2, 2, 5, [6], 0, 0, 1
            if (nums[mid] >= nums[low]) {
                // target 落在 low 和 mid 之间，比如 target == 3：在前半部分查找。
                if (nums[mid] > target && nums[low] <= target) {
                    high = mid - 1;
                }
                // 否则在后半部分查找，比如 target == 1。
                else {
                    low = mid + 1;
                }
            }
            // mid + 1 ~ high 递增：5, 6, 0, [0], 1, 2, 2
            else {
                // target 落在 mid 和 high 之间，比如 target == 1：在后半部分查找。
                if (nums[mid] < target && nums[high] >= target) {
                    low = mid + 1;
                }
                // 否则在后半部分查找，比如 target == 3。
                else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }
}
