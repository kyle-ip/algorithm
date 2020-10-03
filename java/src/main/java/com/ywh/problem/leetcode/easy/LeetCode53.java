package com.ywh.problem.leetcode.easy;

/**
 * 连续子序列的最大和
 * [动态规划] [分治] [数组]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode53 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int maxSumOfSubArray(int[] nums) {
        // 遍历数组，使用变量 cur 记录当前累计和、max 记录历史最大累计和。
        int max = Integer.MIN_VALUE, cur = 0;
        for (int num : nums) {
            // 当 cur > 0，表示当前累计和仍存在成为全局最大的可能性，所以继续累计数组中的元素。
            if (cur > 0) {
                cur += num;
            }
            // 当 cur <= 0，表示无论下一个元素是正是负，都没有必要把当前累计和加上（因为会使总和变小），因此抛弃当前累计和、从当前元素重新开始累计。
            else {
                cur = num;
            }
            // 每轮计算都把 cur 与 max 比较，取较大者保存在 max 可确保 max 始终保持最大的累计和。
            max = Math.max(cur, max);
        }
        return max;
    }

}
