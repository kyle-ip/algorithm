package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.easy.LeetCode303;

/**
 * 二维区域和检索 - 矩阵不可变
 * [数组] [动态规划]
 *
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 * 示例:
 *      给定 matrix = [
 *        [3, 0, 1, 4, 2],
 *        [5, 6, 3, 2, 1],
 *        [1, 2, 0, 1, 5],
 *        [4, 1, 0, 1, 7],
 *        [1, 0, 3, 0, 5]
 *      ]
 *      sumRegion(2, 1, 4, 3) -> 8
 *      sumRegion(1, 1, 2, 2) -> 11
 *      sumRegion(1, 2, 2, 4) -> 12
 * 说明:
 *      你可以假设矩阵不可变。
 *      会多次调用 sumRegion 方法。
 *      你可以假设 row1 ≤ row2 且 col1 ≤ col2。
 *
 * @author ywh
 * @since 08/04/2020
 */
public class LeetCode304 {

    /**
     * 这是 {@link LeetCode303} 的二维版本
     */
    public static class NumMatrixImmutable {

        private final int[][] prefixSum;

        /**
         * Time: O(m*n), Space: O(m*n)
         *
         * @param matrix
         */
        public NumMatrixImmutable(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
                prefixSum = new int[1][1];
                return;
            }
            int m = matrix.length, n = matrix[0].length;
            prefixSum = new int[m + 1][n + 1];

            // prefixSum[i][j] 表示 matrix[0][0] 到 matrix[i-1][j-1] 的子矩阵元素和
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    // prefixSum[i - 1][j - 1] 重叠，被多算了一遍，要减去
                    prefixSum[i][j] = prefixSum[i][j - 1] + prefixSum[i - 1][j] - prefixSum[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }

        /**
         *
         * @param row1
         * @param col1
         * @param row2
         * @param col2
         * @return
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            // prefixSum[row1][col1] 重叠，被多算了一遍，要加上
            return prefixSum[row2 + 1][col2 + 1] - prefixSum[row2 + 1][col1] - prefixSum[row1][col2 + 1] + prefixSum[row1][col1];
        }
    }
}
