package com.ywh.problem.leetcode.hard;

import com.ywh.problem.leetcode.medium.LeetCode11;

/**
 * 雨后盛水量
 * [数组] [双指针] [栈]
 *
 * @author ywh
 * @since 12/01/2020
 */
public class LeetCode42 {

    /**
     * 动态规划解法
     *
     * Time: O(n), Space: O(n)
     *
     * @param height
     * @return
     */
    public int waterCanBeTrap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int n = height.length, leftMax = -1, rightMax = -1, water = 0;
        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            leftMax = Math.max(leftMax, height[i]);
            d[i] = leftMax;
        }
        for (int i = n-1; i >= 0; --i) {
            rightMax = Math.max(rightMax, height[i]);
            d[i] = Math.min(d[i], rightMax);
            water += (d[i] - height[i]);
        }
        return water;
    }

    /**
     * 双指针解法
     * 类似 {@link LeetCode11}
     *
     * Time: O(n), Space: O(1)
     *
     * @param height
     * @return
     */
    public int waterCanBeTrapO1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int leftMax = -1, rightMax = -1, water = 0, left = 0, right = height.length - 1;
        // 双指针从两边向中间移动。
        while (left <= right) {
            // 每轮循环更新左边高度最大值和右边高度最大值。
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // 如果左边高度最大值比右边高度最大值小，表示当前位置可留存雨量取决于左边。
            // 所以总雨量添加左边高度最大值 - 当前左边高度，同时左指针右移。
            if (leftMax < rightMax) {
                water += leftMax - height[left++];
            }
            // 右边同理。
            else {
                water += rightMax - height[right--];
            }
        }
        return water;
    }

}
