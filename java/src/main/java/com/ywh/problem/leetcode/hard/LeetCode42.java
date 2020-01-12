package com.ywh.problem.leetcode.hard;

/**
 * 雨后盛水量
 * [数组] [双指针] [栈]
 *
 * @author ywh
 * @since 12/01/2020
 */
public class LeetCode42 {

    /**
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
     * Time: O(n), Space: O(1)
     *
     * @param height
     * @return
     */
    public int waterCanBeTrapO1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int leftMax = -1, rightMax = -1, water = 0;
        int i = 0, j = height.length - 1;
        while (i <= j) {
            leftMax = Math.max(leftMax, height[i]);
            rightMax = Math.max(rightMax, height[j]);
            if (leftMax < rightMax) {
                water += (leftMax - height[i++]);
            } else {
                water += (rightMax - height[j--]);
            }
        }
        return water;
    }

}
