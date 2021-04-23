package com.ywh.problem.leetcode.medium;

/**
 * 寻找旋转排序数组中的最小值
 * [数组] [二分查找]
 * 
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。
 * 请找出其中最小的元素。
 * 示例 1：
 *      输入：nums = [3,4,5,1,2]
 *      输出：1
 * 示例 2：
 *      输入：nums = [4,5,6,7,0,1,2]
 *      输出：0
 * 示例 3：
 *      输入：nums = [1]
 *      输出：1
 * 提示：
 *      1 <= nums.length <= 5000
 *      -5000 <= nums[i] <= 5000
 *      nums 中的所有整数都是 唯一 的
 *      nums 原来是一个升序排序的数组，但在预先未知的某个点上进行了旋转
 *
 * @author ywh
 * @since 26/11/2019
 */
public class LeetCode153 {

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {

                high = mid;
            }
        }
        return nums[low];
    }

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int findMinEarlyReturn(int[] nums) {
        int low = 0, high = nums.length - 1;

        // 旋转后的数组只有两种情况：
        // 1. 分两半分别递增，最小值在中间；
        // 2. 全数组递增，最小值在最左端
        while (low < high) {

            // 子数组是从 low 到 high 递增的，直接返回 low。
            if (nums[low] < nums[high]) {
                return nums[low];
            }

            // 否则 low 与 high 之间还是分成两部分，继续二分查找。
            int mid = low + (high - low) / 2;

            // low 比 high 大、mid 比 high 大，表示旋转的位置在右边，且不可能是 mid 的位置。
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            }
            // 最小值可能正是 mid 的位置，因此不能错过，必须包括在搜索范围内。
            else {
                high = mid;
            }

        }
        return nums[low];
    }
}
