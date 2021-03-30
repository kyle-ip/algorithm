package com.ywh.problem.leetcode.medium;

/**
 * 寻找峰值
 * [数组] [二分搜索]
 *
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * 示例 1:
 *      输入: nums = [1,2,3,1]
 *      输出: 2
 *      解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 *      输入: nums = [1,2,1,3,5,6,4]
 *      输出: 1 或 5
 *      解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *           或者返回索引 5， 其峰值元素为 6。
 * 说明:
 *      你的解法应该是 O(logN) 时间复杂度的。
 * 提示：
 *      1 <= nums.length <= 1000
 *      -2^31 <= nums[i] <= 2^31 - 1
 *      对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * 进阶：你可以实现时间复杂度为 O(logN) 的解决方案吗？
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
        int low = 0, high = nums.length - 1, left, right;
        while (low < high) {
            int mid = low + (high - low) / 2;

            // 取 mid 左边和右边的值，如果 mid 落在边界上，则把它的左/右边的值设为最小值。
            left = mid - 1 >= 0 ? nums[mid - 1] : Integer.MIN_VALUE;
            right = mid + 1 < nums.length ? nums[mid + 1] : Integer.MIN_VALUE;

            // 如果 mid 比左右的值都大，直接返回。
            if (nums[mid] > left && nums[mid] > right) {
                return mid;
            }
            // 如果 mid 左边的值比它大，则说明波峰在左边，否则在右边。
            // [1, 2, 3, 1, 0]
            //           m
            if (left > nums[mid]) {
                high = mid - 1;
            }
            // [1, 2, 1, 3, 5, 6, 4]
            //           m
            else {
                low = mid + 1;
            }
        }
        return low;
    }
}
