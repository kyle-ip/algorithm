package com.ywh.problem.leetcode.medium;

/**
 * 数组的下一个排列
 * [数组]
 *
 * @author ywh
 * @since 02/11/2019
 */
public class LeetCode31 {

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 从低位到高位遍历，尝试判断相邻两位交换是否会增大；
     * 直到满足 nums[k + 1] > nums[k]，则 n - 1 开始遍历到 k + 1，取出第一个比 nums[k] 大的元素，与 nums[k] 交换；
     * 最后再对 k + 1 ~ n - 1按从小到大排列即可（由于此时 k + 1 ~ n - 1 默认就是递减，所以这部分首尾两两交换元素即可从小到大排列）。
     * <p>
     * Time: O(n), Space: O(1)
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }


        // 游标从倒数第二个元素开始
        // 9, 1, 8, 4, [2], 1
        int n = nums.length, k = n - 2;

        // 1. 从尾到头遍历，找到满足 nums[k + 1] > nums[k] 的 k（表示这部分元素是从右到左递增，不可能通过交换产生更大的排列）
        // 9, [1], (8, 4, 2, 1)
        while (k >= 0 && nums[k + 1] <= nums[k]) {
            k--;
        }

        // 2. 找到 nums[k + 1] > nums[k]，则从最右开始、找到第一个比 nums[k] 大的数字
        // 9, [1], 8, 4, {2}, 1
        if (k >= 0) {
            int i = n - 1;
            while (i > k && nums[i] <= nums[k]) {
                i--;
            }
            // 9, 1, 8, 4, 2, 1 => 9, 2, 8, 4, 1, 1
            swap(nums, i, k);
        }

        // 最后再对 k + 1 ~ n - 1 按从小到大排列（首尾两两交换）
        // 9, 2, 8, 4, 1, 1 => 9, 2, 1, 1, 4, 8
        for (int i = k + 1, j = n - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }
}
