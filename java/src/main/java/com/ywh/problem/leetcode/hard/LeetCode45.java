package com.ywh.problem.leetcode.hard;

/**
 * 跳完数组的最少跳数
 * [数组] [贪心]
 *
 * @author ywh
 * @since 2019/11/29/029
 */
public class LeetCode45 {

    /**
     * TODO 暂时未理解
     *
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int numOfJumpToLast(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length, max = 0;
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            if (max >= n - 1) {
                return d[n - 1];
            }
            if (max < i) {
                return -1;
            }
            max = Math.max(max, i + nums[i]);
            int last = Math.min(max, n-1);
            for (int j = last; j > i && d[j] == 0; --j) {
                d[j] = d[i] + 1;
            }
        }
        
        return -1;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int numOfJumpToLastO1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        // max 表示从当前位置下累计最远可达位置，jumps 表示已跳步数，curEnd 表示上次跳步后最远可达位置的下标（用于判断是否需要跳）
        int n = nums.length, max = 0, jumps = 0, curEnd = 0;
        for (int i = 0; i < n; i++) {

            // 如最远可达位置大于等于数组长度（末尾），返回最后一跳即可
            if (max >= n - 1) {
                return jumps + 1;
            }

            // 如最远可达位置小于当前下标，则无法跳到末尾，直接返回 -1
            if (max < i) {
                return -1;
            }
            // 如当前下标大于上次跳步后最远可达位置的下标，表示如果不跳步则无法到达当前位置，向后跳 1 次
            if (i > curEnd) {
                jumps++;
                curEnd = max;
            }
            max = Math.max(max, i + nums[i]);
        }

        return -1;
    }
}
