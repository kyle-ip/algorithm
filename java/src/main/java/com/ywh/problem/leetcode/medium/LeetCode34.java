package com.ywh.problem.leetcode.medium;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * [数组] [二分查找]
 * 
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 进阶：
 *      你可以设计并实现时间复杂度为 O(log(n)) 的算法解决此问题吗？
 * 示例 1：
 *      输入：nums = [5,7,7,8,8,10], target = 8
 *      输出：[3,4]
 * 示例 2：
 *      输入：nums = [5,7,7,8,8,10], target = 6
 *      输出：[-1,-1]
 * 示例 3：
 *      输入：nums = [], target = 0
 *      输出：[-1,-1]
 * 提示：
 *      0 <= nums.length <= 10^5
 *      -10^9 <= nums[i] <= 10^9
 *      nums 是一个非递减数组
 *      -10^9 <= target <= 10^9
 *
 * @author ywh
 * @since 2019/11/29/029
 */
public class LeetCode34 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] findFirstAndLastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int n = nums.length, start = -1, end = -1;
        for (int i = 0; i < n; i++) {
            if (start == -1 && nums[i] == target) {
                start = i;
            }
            if (nums[i] == target) {
                end = i;
            }
        }
        return new int[]{start, end};
    }

    /**
     * 查找 target 的结束下标：即当 nums 中有多个 target 时，返回最后一个的下标，否则返回右界
     *
     * @param nums
     * @param target
     * @return
     */
    private int binarySearchLastOne(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            }
            // 找到目标值时不返回，继续在右半边查找，因此可以找到目标值的结束下标。
            // nums[mid] == target 时，nums[mid+1] 也可能是 target，继续在右半边搜索。
            else {
                low = mid + 1;
            }
        }

        // [5, 7, 7, 8, 8, 10]
        //              h   l
        return high;
    }

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] binarySearchFirstAndLastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        // [5, 7, 7, 8, 8, 10], target
        //              ↑

        // [5, 7, 7, 8, 8, 10], target-1
        //        ↑
        int end = binarySearchLastOne(nums, target);

        // target - 1 的结束下标，再 +1 正好就是 target 的开始下标
        int start = binarySearchLastOne(nums, target - 1) + 1;
        if (start >= 0 && start <= end && end < nums.length) {
            return new int[]{start, end};
        } else {
            return new int[]{-1, -1};
        }
    }
}
