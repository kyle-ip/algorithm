package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.easy.LeetCode303;

/**
 * 不可变矩阵的部分和查询
 * [数组] [动态规划]
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

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // prefixSum[row1][col1] 重叠，被多算了一遍，要加上
            return prefixSum[row2 + 1][col2 + 1] - prefixSum[row2 + 1][col1] - prefixSum[row1][col2 + 1] + prefixSum[row1][col1];
        }

    }


}
