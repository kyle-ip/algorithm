package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.hard.LeetCode85;

/**
 * 0/1 矩阵中的最大正方形
 * [动态规划] [数组] [栈]
 *
 * @author ywh
 * @since 16/11/2019
 */
public class LeetCode221 {

    /**
     * Time: O(m*n), Space: O(n)
     *
     * @param heights
     * @return
     */
    private int largestSquareLengthInHistogram(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int top = 0, n = heights.length, maxLength = 0;
        int[] stack = new int[n + 1];
        for (int right = 0; right <= n; right++) {
            int h = right == n ? 0 : heights[right];
            while (top != -1 && h < heights[stack[top]]) {
                int idx = stack[top--];
                int left = top != -1 ? stack[top] : -1;

                // 取宽和高的较小者为边长
                int len = Math.min(heights[idx], right - left - 1);
                maxLength = Math.max(maxLength, len);
            }
            stack[++top] = right;
        }
        return maxLength;
    }

    /**
     * 分层单调栈解法，参考 {@link LeetCode85}
     * <p>
     * Time: O(m*n), Space: O(n)
     *
     * @param matrix
     * @return
     */
    public int maximalSquareHistogram(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length, maxLength = 0;
        int[] heights = new int[n + 1];
        for (char[] row : matrix) {
            for (int i = 0; i < n; i++) {
                heights[i] = row[i] == '1' ? heights[i] + 1 : 0;
            }
            maxLength = Math.max(maxLength, largestSquareLengthInHistogram(heights));
        }

        return maxLength * maxLength;
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    /**
     * 把求最大面积转化成求最大边长，其平方为最大面积
     * <p>
     * Time: O(m*n), Space: O(m*n)
     *
     * @param matrix
     * @return
     */
    public int maximalSquareDP(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        // 状态 d[i][j] 表示只包含字符 1 并且右下角在 (i, j) 的最大正方形对应的边长
        int m = matrix.length, n = matrix[0].length, len = 0;
        int[][] d = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 第一行、第一列，或当前位置为 0
                if (i == 0 || j == 0 || matrix[i][j] == '0') {
                    d[i][j] = matrix[i][j] == '1' ? 1 : 0;
                } else {
                    // 取以左、上、左上为右下顶点的最大边长状态值的最小值
                    d[i][j] = min(d[i - 1][j - 1], d[i - 1][j], d[i][j - 1]) + 1;
                }
                len = Math.max(len, d[i][j]);
            }
        }
        return len * len;
    }

    /**
     * Time: O(m*n), Space: O(n)
     *
     * @param matrix
     * @return
     */
    public int maximalSquareDPOn(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        // 用 pre 记录更新前的 d[i][j] 代替 d[i - 1][j - 1] 的值实现降维
        // 另外 d[i - 1][j] => d[j]、d[i][j - 1] => d[j - 1]
        int m = matrix.length, n = matrix[0].length, maxLen = 0, pre = 0, tmp;
        int[] d = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tmp = d[j];
                if (i == 0 || j == 0 || matrix[i][j] == '0') {
                    d[j] = matrix[i][j] == '1' ? 1 : 0;
                } else {
                    d[j] = min(pre, d[j - 1], d[j]) + 1;
                }
                maxLen = Math.max(maxLen, d[j]);
                pre = tmp;
            }
        }
        return maxLen * maxLen;
    }
}
