package com.ywh.problem.leetcode.medium;

/**
 * 容纳最多水的凹槽容量
 * [数组] [双指针]
 *
 * @author ywh
 * @since 2019/2/21
 */
public class LeetCode11 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param height
     * @return
     */
    public int maxWater(int[] height) {
        int left = 0, right = height.length - 1, max = 0, s;
        while (left < right) {
            // 当前面积，左右高度中的较小者 * 左右距离
            s = (right - left) * Math.min(height[left], height[right]);
            max = Math.max(max, s);

            // 比较左右指针所指高度，固定较高的指针（宽度随指针移动变小、高度由于取决于原来的较小者只能不变或变小，面积必然变小，因此只能固定较高者）
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
