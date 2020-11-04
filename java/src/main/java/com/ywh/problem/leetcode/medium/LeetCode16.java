package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 求和最接近目标值的三个数
 * [双指针] [数组]
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

        for (int k = nums.length-1; k >= 2; --k) {
            int i = 0, j = k-1;
            while (i < j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
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
