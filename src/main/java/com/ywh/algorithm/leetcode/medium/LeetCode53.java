package com.ywh.algorithm.leetcode.medium;

/**
 * 连续子序列的最大和
 * [动态规划] [分治] [数组]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode53 {

    /**
     * 遍历数组，使用变量 cur 记录当前累计和、max 记录历史最大累计和；
     * 当 cur > 0，表示当前累计和仍存在成为全局最大的可能性，所以继续累计数组中的元素；
     * 当 cur <= 0，表示无论下一个元素是正是负，都没有必要把当前累计和加上，因此抛弃当前累计和、从当前元素开始累计；
     * 每轮计算都把 cur 与 max 比较，取较大者保存在 max 可确保 max 始终保持最大的累计和
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int maxSumOfSubArray(int[] nums) {
        int max = Integer.MIN_VALUE, cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (cur > 0) {
                cur += nums[i];
            } else {
                cur = nums[i];
            }
            max = Math.max(cur, max);
        }
        return max;
    }

}
