package com.ywh.problem.leetcode.hard;

/**
 * 最大矩形
 * [数组] [栈]
 *
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例 1：
 *      输入：matrix = [
 *              ["1","0","1","0","0"],
 *              ["1","0","1","1","1"],
 *              ["1","1","1","1","1"],
 *              ["1","0","0","1","0"]
 *           ]
 *      输出：6
 * 示例 2：
 *      输入：matrix = []
 *      输出：0
 * 示例 3：
 *      输入：matrix = [["0"]]
 *      输出：0
 * 示例 4：
 *      输入：matrix = [["1"]]
 *      输出：1
 * 示例 5：
 *      输入：matrix = [["0","0"]]
 *      输出：0
 * 提示：
 *      rows == matrix.length
 *      cols == matrix[0].length
 *      0 <= row, cols <= 200
 *      matrix[i][j] 为 '0' 或 '1'
 *
 * @author ywh
 * @since 16/11/2019
 */
public class LeetCode85 {

    /**
     *
     * @param heights
     * @return
     */
    private int largestRectangleInHistogram2(int[] heights) {
        int max = 0, n = heights.length;
        for (int i = 0; i < n; i++) {
            int l = i, r = i;
            for (; l >= 0 && heights[l] >= heights[i]; l--);
            for (; r < n && heights[r] >= heights[i]; r++);
            max = Math.max(max, heights[i] * (r - l - 1));
        }
        return max;
    }

    /**
     *
     * @param heights
     * @return
     */
    private int largestRectangleInHistogram(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = 0, n = heights.length, top = -1;
        int[] stack = new int[n + 1];
        for (int r = 0; r <= n; r++) {
            int h = r == n ? 0 : heights[r];
            while (top != -1 && h < heights[stack[top]]) {
                int idx = stack[top--];
                int l = top != -1 ? stack[top] : -1;
                max = Math.max(max, heights[idx] * (r - l - 1));
            }
            stack[++top] = r;
        }
        return max;
    }

    /**
     * 从上至下，每层叠加看作一个直方图（第一层高度为 1、第二层为 2...），利用 {@link LeetCode84} 的思路求解；
     * 每一层的高度值叠加到上一层的高度值，如果当前高度值为 0，则重新置 0；
     * 每一层求出直方图最大矩形面积，取全局最大即可。
     *
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
            // 设置直方图的高度：累加或重置。
            for (int j = 0; j < n; j++) {
                heights[j] = row[j] == '1' ? heights[j] + 1 : 0;
            }
            max = Math.max(max, largestRectangleInHistogram2(heights));
        }
        return max;
    }
}
