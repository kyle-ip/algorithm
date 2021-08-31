package com.ywh.problem.leetcode.medium;

/**
 * 搜索旋转排序数组 II
 * [数组] [二分查找]
 * 
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0],
 * nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 * 示例 1：
 *      输入：nums = [2,5,6,0,0,1,2], target = 0
 *      输出：true
 * 示例 2：
 *      输入：nums = [2,5,6,0,0,1,2], target = 3
 *      输出：false
 * 提示：
 *      1 <= nums.length <= 5000
 *      -104 <= nums[i] <= 104
 *      题目数据保证 nums 在预先未知的某个下标上进行了旋转
 *      -104 <= target <= 104
 * 进阶：
 *      这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 *      这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
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
        for (int low = 0, high = nums.length - 1, mid; low <= high; ) {
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
