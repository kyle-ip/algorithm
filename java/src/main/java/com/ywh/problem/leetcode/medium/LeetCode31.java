package com.ywh.problem.leetcode.medium;

/**
 * 下一个排列
 * [数组]
 * 
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * 示例 1：
 *      输入：nums = [1,2,3]
 *      输出：[1,3,2]
 * 示例 2：
 *      输入：nums = [3,2,1]
 *      输出：[1,2,3]
 * 示例 3：
 *      输入：nums = [1,1,5]
 *      输出：[1,5,1]
 * 示例 4：
 *      输入：nums = [1]
 *      输出：[1]
 * 提示：
 *      1 <= nums.length <= 100
 *      0 <= nums[i] <= 100
 *
 * @author ywh
 * @since 02/11/2019
 */
public class LeetCode31 {

    /**
     *
     * @param nums
     * @param i
     * @param j
     */
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
        int n = nums.length, k = n - 2;

        // 1. 游标从倒数第二个元素开始，从右向左遍历，找到第一个递减的位置。
        // 表示此前的元素一直递增，无法找到一个比 nums[n-1] 大的来交换。
        // 9, [1], (8, 4, 2, 1)
        //     k   k+1
        for (; k >= 0 && nums[k] >= nums[k + 1] ; k--) ;

        // 2. 从最右开始、找到第一个比 nums[k] 大的数字 nums[i]，交换 nums[i] 和 nums[k]。

        // |   |                |   |
        // |   | |              | | |
        // | | | | |      =>    | | | | |
        // | | | | | |          | | | | | |
        //   k       i            k   i

        //         <-
        //     +----------+
        //     |          ↓
        // 9, [1], 8, 4, {2}, 1
        //     k          i
        if (k >= 0) {
            int i = n - 1;
            for (; i > k && nums[i] <= nums[k]; i--) ;
            swap(nums, i, k);
        }

        // |   |                |         |
        // | | |                | |       |
        // | | | | |      =>    | |   | | |
        // | | | | | |          | | | | | |
        //   k i     j    =>      k   j i

        // 3. 最后再对 [k+1, n-1] 按递增排列：由于 [k+1, n-1] 此时是递减的，首尾两两交换即可。
        // 9, 2, [8, 4, 1, 1] => 9, 2, [1, 1, 4, 8]
        //       k+1      n-1
        for (int l = k + 1, r = n - 1; l < r;) {
            swap(nums, l++, r--);
        }
    }
}
