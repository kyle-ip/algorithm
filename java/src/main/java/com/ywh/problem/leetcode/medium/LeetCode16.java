package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 求和最接近目标值的三个数
 * [双指针] [数组]
 * 
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 示例：
 *      输入：nums = [-1,2,1,-4], target = 1
 *      输出：2
 *      解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * 提示：
 *      3 <= nums.length <= 10^3
 *      -10^3 <= nums[i] <= 10^3
 *      -10^4 <= target <= 10^4
 *
 * @author ywh
 * @since 01/12/2019
 */
public class LeetCode16 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int ret = 0, min = Integer.MAX_VALUE;
        Arrays.sort(nums);

        for (int k = nums.length - 1; k >= 2; --k) {
            int i = 0, j = k - 1;
            while (i < j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return sum;
                }
                if (sum < target) {
                    ++i;
                } else {
                    --j;
                }
                int diff = Math.abs(target - sum);
                if (diff < min) {
                    ret = sum;
                    min = diff;
                }
            }
        }
        return ret;
    }
}
