package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.hard.LeetCode42;

/**
 * 盛最多水的容器
 * [数组] [双指针]
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai)。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器。
 * 示例 1：
 *      输入：[1,8,6,2,5,4,8,3,7]
 *      输出：49
 *      解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 *      输入：height = [1,1]
 *      输出：1
 * 示例 3：
 *      输入：height = [4,3,2,1,4]
 *      输出：16
 * 示例 4：
 *      输入：height = [1,2,1]
 *      输出：2
 * 提示：
 *      n = height.length
 *      2 <= n <= 3 * 10^4
 *      0 <= height[i] <= 3*10^4
 *
 * @author ywh
 * @since 2019/2/21
 */
public class LeetCode11 {

    /**
     * 类似 {@link LeetCode42}
     *
     * Time: O(n), Space: O(1)
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int ret = 0;
        for (int l = 0, r = height.length - 1; l < r; ) {
            // 当前面积，高度（左右高度中的较小者） * 宽度（左右距离）。
            ret = Math.max(ret, (r - l) * Math.min(height[l], height[r]));

            // 比较左右指针所指高度，固定较高的指针：宽度随指针移动变小，要使乘积变大必然要选取最大的高度。
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return ret;
    }
}
