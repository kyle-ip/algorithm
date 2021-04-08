package com.ywh.problem.leetcode.easy;

/**
 * 有序数组的平方
 * [数组] [双指针]
 *
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * 示例 1：
 *      输入：nums = [-4,-1,0,3,10]
 *      输出：[0,1,9,16,100]
 *      解释：平方后，数组变为 [16,1,0,9,100]
 *      排序后，数组变为 [0,1,9,16,100]
 * 示例 2：
 *      输入：nums = [-7,-3,2,3,11]
 *      输出：[4,9,9,49,121]
 * 提示：
 *      1 <= nums.length <= 10^4
 *      -10^4 <= nums[i] <= 10^4
 *      nums 已按 非递减顺序 排序
 * 进阶：
 *      请你设计时间复杂度为 O(n) 的算法解决本问题
 *
 * @author ywh
 * @since 4/8/2021
 */
public class LeetCode977 {

    /**
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        // 双指针分别从左右出发，从右到左更新结果数组，因为存在负数，不能从左到右（两边的平方大于中间的平方）：
        // 比如对于 [-4,-1,0,3,10]，左值平方是 16，右值的平方是 100，则左边更新了 16，实际上中间还有 0*0 == 0。
        for (int l = 0, r = n - 1, i = n - 1; l <= r; i--) {
            // 已知原数组有序，如果左右都是负数，则左值平方和必然大于右值平方和；
            // 如果左边是负数、右边是正数，且左值的绝对值大于右值，则左值的平方和也大于右值。
            // 因此判断可简化为取左值的相反数。
            // if (nums[l] * nums[l] > nums[r] * nums[r]) {
            if (-nums[l] > nums[r]) {
                ret[i] = nums[l] * nums[l];
                ++l;
            } else {
                ret[i] = nums[r] * nums[r];
                --r;
            }
        }
        return ret;
    }

}
