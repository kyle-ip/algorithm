package com.ywh.problem.leetcode.medium;

/**
 * 长度最小的子数组
 * [数组] [双指针] [二分搜索]
 *
 * @author ywh
 * @since 2020/9/14/014
 */
public class LeetCode209 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int cnt = Integer.MAX_VALUE ,n = nums.length, left = 0, right = 0, sum = 0;
        if (n == 0) {
            return 0;
        }

        while (right < n) {
            // 每轮循环把最右边的元素添加到 sum 中。
            sum += nums[right];

            // 判断是否已满足题目中 sum 大于等于 s 的条件，是则计算一次 cnt，并减去最左边的值（表示计算下一个连续子数组的和）。
            // 由于存在最右边的元素过大、导致算入 nums[right] 后超出 s 很多的情况（即减去 nums[left] 后仍然大于 s），因此要使用 while。
            while (sum >= s) {
                cnt = Math.min(cnt, right - left + 1);
                sum -= nums[left++];
            }
            right++;
        }

        // 求最小值应把变量初始化为最大值，如果 cnt 一次都未被计算（仍为初始化的值），即所有元素之和小于 s，依题目要求不满足条件时返回 0。
        return cnt == Integer.MAX_VALUE? 0: cnt;
    }
}
