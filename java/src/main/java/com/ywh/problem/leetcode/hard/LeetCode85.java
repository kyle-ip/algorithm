package com.ywh.problem.leetcode.hard;

/**
 * 0/1 矩阵中的最大矩形
 * [数组] [栈]
 *
 * @author ywh
 * @since 16/11/2019
 */
public class LeetCode85 {

    private int largestRectangleInHistogram(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = 0, n = heights.length, top = -1;
        int[] stack = new int[n + 1];
        for (int right = 0; right <= n; right++) {
            int h = right == n ? 0 : heights[right];
            while (top != -1 && h < heights[stack[top]]) {
                int idx = stack[top--];
                int left = top != -1 ? stack[top] : -1;
                max = Math.max(max, heights[idx] * (right - left - 1));
            }
            stack[++top] = right;
        }
        return max;
    }

    /**
     * 从上至下，每层叠加看作一个直方图（第一层高度为 1、第二层为 2...），利用 {@link LeetCode84} 单调栈的思路求解；
     * 每一层的高度值叠加到上一层的高度值，如果当前高度值为 0，则重新置 0；
     * 每一层求出直方图最大矩形面积，取全局最大即可。
     * <p>
     * Time: O(m*n), Space: O(n)
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix[0].length, max = 0;
        int[] heights = new int[n];

        for (char[] row : matrix) {

            // 设置直方图的高度
            for (int j = 0; j < n; j++) {
                heights[j] = row[j] == '1' ? heights[j] + 1 : 0;
            }
            max = Math.max(max, largestRectangleInHistogram(heights));
        }

        return max;
    }
}
