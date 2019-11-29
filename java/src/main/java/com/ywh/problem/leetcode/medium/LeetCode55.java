package com.ywh.problem.leetcode.medium;

/**
 * 跳数组
 * [数组] [贪心]
 *
 * @author ywh
 * @since 2019/11/29/029
 */
public class LeetCode55 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public boolean canJumpToLast(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        // max 记录从当前位置下最远可达位置，可能为当前下标 + 步数（2, [4], 0, 1），或前面累计的最大值（4, [2], 0, 0, 0, 1）
        int n = nums.length, max = 0;
        for (int i = 0; i < n; i++) {
            // 如最远可达位置大于等于数组长度（末尾），直接返回 true
            if (max >= n - 1) {
                return true;
            }

            // 如最远可达位置小于当前下标，表示中间存在一或多个 0、且前面的步数不足以跳过这些 0（比如 2, 0, 0, [0], 2），提早返回 false
            if (max < i) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }

        // 循环结束都无法到达最后，返回错误
        return false;
    }

}
