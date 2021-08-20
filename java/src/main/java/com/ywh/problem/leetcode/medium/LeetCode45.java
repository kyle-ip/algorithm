package com.ywh.problem.leetcode.medium;

/**
 * 跳跃游戏 II
 * [数组] [贪心]
 * 
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 示例:
 *      输入: [2,3,1,1,4]
 *      输出: 2
 *      解释: 跳到最后一个位置的最小跳跃数是 2。
 *            从下标为 0 跳到下标为 1 的位置，跳  1  步，然后跳  3  步到达数组的最后一个位置。
 * 说明:
 *      假设你总是可以到达数组的最后一个位置。
 *
 * @author ywh
 * @since 2019/11/29/029
 */
public class LeetCode45 {

    /**
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

    /**
     * 参考 {@link LeetCode55}
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {

        // steps 记录步数。
        int steps = 0, n = nums.length;

        // end 记录边界（应该跳到的位置），max 记录从当前点出发可到达的最远位置，一定大于等于最后一个位置，因此最后一步必然跳出数组，不需要访问最后一个元素。
        for (int end = 0, max = 0, i = 0; i < n - 1; i++) {
            // 每轮循环找到从当前位置起跳、可到达的最远位置（从 i 跳跃 nums[i] 位）。
            // 比如 [2, 3, 1, 2, 4, 2, 3]，从下标 0 出发，最远可到达下标 2。
            // 在下标 0 可到达的位置中（0、1、2），下标 1 的值是 3、下标 2 的值是 1，即再从 1 出发可到达位置最远。
            // 所以应该从 0 跳到 1。
            max = Math.max(max, i + nums[i]);

            // 如果遍历走到边界，则更新边界为之前记录的、可到达的最远位置，并增加步数。
            if (i == end) {
                end = max;
                steps++;
            }
        }
        return steps;
    }
}
