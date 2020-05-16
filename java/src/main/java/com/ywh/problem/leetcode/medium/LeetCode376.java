package com.ywh.problem.leetcode.medium;

/**
 * 最长摆动子序列的长度
 * [贪心] [动态规划]
 *
 * @author ywh
 * @since 16/05/2020
 */
public class LeetCode376 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLengthGreedy(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int i = 1, len = 1;

        // 跳过相同元素
        while (i < n && nums[i] == nums[i-1]) {
            ++i;
        }
        while (i < n) {
            // 找到相邻递增的位置
            if (nums[i] > nums[i-1]) {
                ++len;
            }
            // 跳过相邻递增
            while (i < n && nums[i] >= nums[i-1]) {
                ++i;
            }
            // 找到相邻递减的
            if (i < n) {
                ++len;
            }
            // 跳过相邻递减的
            while (i < n && nums[i] <= nums[i-1]) {
                ++i;
            }
        }
        return len;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLengthDP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = 1;
        down[0] = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] > nums[i-1]) {
                up[i] = down[i-1] + 1;
                down[i] = down[i-1];
            } else if (nums[i] < nums[i-1]) {
                down[i] = up[i-1] + 1;
                up[i] = up[i-1];
            } else {
                up[i] = up[i-1];
                down[i] = down[i-1];
            }
        }
        return Math.max(up[n-1], down[n-1]);
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLengthDPO1(int[] nums) {
        // d[i] 表示下标 0~i 的子数组里，最长摆动子序列长度
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;

        // up、down 表示下/上摆结尾最长摆动子序列的长度（每换一次方向，取值为对方的值 + 1）
        int up = 1, down = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] > nums[i-1]) {
                up = down + 1;
            } else if (nums[i] < nums[i-1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}
