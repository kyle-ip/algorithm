package com.ywh.problem.leetcode.hard;

import com.ywh.problem.leetcode.medium.LeetCode11;

/**
 * 接雨水
 * [数组] [双指针] [栈]
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 示例 1：
 *                                   |
 *                           |       | |   |
 *                       |   | |   | | | | | |
 *      输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 *      输出：6
 *      解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *                               |
 *                     |         |
 *                     |     |   |
 *                     | |   | | |
 *                     | |   | | |
 *      输入：height = [4,2,0,3,2,5]
 *      输出：9
 * 提示：
 *      n == height.length
 *      1 <= n <= 3 * 10^4
 *      0 <= height[i] <= 10^5
 *
 * @author ywh
 * @since 12/01/2020
 */
public class LeetCode42 {

    /**
     * 动态规划解法：两次扫描 + dp 数组开销。
     *
     * Time: O(n), Space: O(n)
     *
     * @param height
     * @return
     */
    public int waterCanBeTrap(int[] height) {
        int n = height.length, leftMax = -1, rightMax = -1, water = 0;
        int[] dp = new int[n];
        // 从左到右求最大高度值，dp 记录截至当前位置，所遇到的高度最大值。
        for (int i = 0; i < n; ++i) {
            leftMax = Math.max(leftMax, height[i]);
            dp[i] = leftMax;
        }
        // 从右到左求高度最大值，同时更新 dp，取两次求得高度最大值之间的最小者（木桶原理）：
        //                   |
        //             |     |
        //          |  |  |  |
        // 比如对于 [1, 2, 1, 3]
        //                ↑         对于该位置，从左到右最高为 2，从右到左最高为 3，取 2。
        // 则蓄水量为该值减去当前位置高度，即 2 - 1 == 1。
        for (int i = n - 1; i >= 0; --i) {
            rightMax = Math.max(rightMax, height[i]);
            dp[i] = Math.min(dp[i], rightMax);
            water += (dp[i] - height[i]);
        }
        return water;
    }

    /**
     * 双指针解法：一次扫描，无额外开销。
     * 类似 {@link LeetCode11}，区别在于本题是求极值之和，{@link LeetCode11} 是求最大面积。
     *
     * Time: O(n), Space: O(1)
     *
     * @param height
     * @return
     */
    public int waterCanBeTrapO1(int[] height) {
        int ret = 0;
        // 双指针从两边向中间移动。
        for (int lMax = 0, rMax = 0, l = 0, r = height.length - 1; l < r; ) {
            // 每轮循环更新左边高度最大值和右边高度最大值。
            lMax = Math.max(lMax, height[l]);
            rMax = Math.max(rMax, height[r]);

            // 如果左边高度最大值比右边高度最大值小，表示当前位置可留存雨量取决于左边。
            // 所以总雨量添加左边高度最大值 - 当前左边高度，同时左指针右移（右边同理）。
            ret += lMax < rMax? lMax - height[l++]: rMax - height[r--];
        }
        return ret;
    }

}
