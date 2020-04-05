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
     * Time: O(n), Space: O(1)
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length, k = n - 2;

        // 1. 游标从倒数第二个元素开始，从尾到头遍历，找到满足 nums[k + 1] > nums[k] 的 k（表示这部分元素是从右到左递增，不可能通过交换产生更大的排列）
        // 9, [1], (8, 4, 2, 1)
        while (k >= 0 && nums[k + 1] <= nums[k]) {
            k--;
        }

        // 2. 从最右开始、找到第一个比 nums[k] 大的数字 nums[i]，交换 nums[i] 和 nums[k]
        // 9, [1], 8, 4, {2}, 1
        if (k >= 0) {
            int i = n - 1;
            while (i > k && nums[i] <= nums[k]) {
                i--;
            }
            // 9, 1, 8, 4, 2, 1 => 9, 2, 8, 4, 1, 1
            swap(nums, i, k);
        }

        // 3. 最后再对 k + 1 ~ n - 1 按递增排列：由于 k + 1 ~ n - 1 此时是递减的，首尾两两交换即可
        // 9, 2, 8, 4, 1, 1 => 9, 2, 1, 1, 4, 8
        for (int i = k + 1, j = n - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }
}
