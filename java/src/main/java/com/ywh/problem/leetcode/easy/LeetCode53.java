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
            // 当 cur > 0，当前累计和仍存在成为全局最大的可能性，所以继续累计。
            // [1, 2, -4, 5]    此时 cur = 3，虽然 num < 0，但算上它以及后面的 5 还是会比从此截断、只算 1 + 2 要大的，所以这个 -4 要保留。
            //       num
            if (cur > 0) {
                cur += num;
            }
            // 当 cur <= 0，无论下一个元素是正是负，都不应把当前累计和加上（会使总和变小），因此抛弃此前统计的累计和、从当前元素重新开始累计。
            // [1, -5, 3, 2]    此时 cur = -4，如果把前面算上则和为 1 - 5 + 3 = -1，从头开始算、加上后面的 2 则为 5，因此应该从 3 开始。
            //        num
            else {
                cur = num;
            }
            // 每轮计算都把 cur 与 max 比较，取较大者保存在 max 可确保 max 始终保持最大的累计和。
            max = Math.max(cur, max);
        }
        return max;
    }

}
