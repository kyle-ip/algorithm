package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.hard.LeetCode85;

/**
 * 最大正方形
 * [动态规划] [数组] [栈]
 *
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * 示例 1：
 *      输入：matrix = [
 *              ["1","0","1","0","0"],
 *              ["1","0","1","1","1"],
 *              ["1","1","1","1","1"],
 *              ["1","0","0","1","0"]
 *           ]
 *      输出：4
 * 示例 2：
 *      输入：matrix = [
 *              ["0","1"],
 *              ["1","0"]
 *           ]
 *      输出：1
 * 示例 3：
 *      输入：matrix = [["0"]]
 *      输出：0
 * 提示：
 *      m == matrix.length
 *      n == matrix[i].length
 *      1 <= m, n <= 300
 *      matrix[i][j] 为 '0' 或 '1'
 *
 * @author ywh
 * @since 16/11/2019
 */
public class LeetCode221 {

    /**
     * FIXME: bug
     *
     * @param heights
     * @return
     */
    private int largestSquareLengthInHistogram2(int[] heights) {
        int max = 0, n = heights.length;
        for (int i = 0; i < n; i++) {
            int l = i, r = i;
            for (; l >= 0 && heights[l] >= heights[i]; l--);
            for (; r < n && heights[r] >= heights[i]; r++);
            int len = Math.min(l - r - 1, heights[i]);
            max = Math.max(max, len * len);
        }
        return max;
    }

    /**
     * 分层双指针解法，参考 {@link LeetCode85}
     *
     * Time: O(m*n), Space: O(n)
     *
     * @param matrix
     * @return
     */
    public int maximalSquareHistogram(char[][] matrix) {
        int n = matrix[0].length;
        int[] heights = new int[n];
        int max = 0;
        for (char[] chars : matrix) {
            for (int j = 0; j < n; ++j) {
                heights[j] = chars[j] == '1' ? heights[j] + 1 : 0;
            }
            max = Math.max(max, largestSquareLengthInHistogram2(heights));
        }
        return max;
    }

    /**
     * Time: O(m*n), Space: O(n)
     *
     * @param heights
     * @return
     */
    private int largestSquareLengthInHistogram(int[] heights) {
        int top = -1, n = heights.length, max = 0;
        int[] stack = new int[n + 1];
        for (int r = 0; r <= n; r++) {
            int h = r == n ? 0 : heights[r];
            while (top != -1 && h < heights[stack[top]]) {
                int idx = stack[top--];
                int left = top != -1 ? stack[top] : -1;

                // 取宽和高的较小者为边长
                int len = Math.min(heights[idx], r - left - 1);
                max = Math.max(max, len * len);
            }
            stack[++top] = r;
        }
        return max;
    }

    /**
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    /**
     * 把求最大面积转化成求最大边长，其平方为最大面积
     *
     * Time: O(m*n), Space: O(m*n)
     *
     * @param matrix
     * @return
     */
    public int maximalSquareDP(char[][] matrix) {

        // 状态 d[i][j] 表示只包含字符 1 并且右下角在 (i, j) 的最大正方形对应的边长。
        int m = matrix.length, n = matrix[0].length, maxLen = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 当前位置为 0
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                }
                // 第一行、第一列
                else if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    // 取以左、上、左上为右下顶点的最大边长状态值的最小值
                    dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1;
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen * maxLen;
    }

    /**
     * Time: O(m*n), Space: O(n)
     *
     * @param matrix
     * @return
     */
    public int maximalSquareDPOn(char[][] matrix) {
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
