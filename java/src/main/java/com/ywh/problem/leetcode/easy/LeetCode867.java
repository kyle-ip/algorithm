package com.ywh.problem.leetcode.easy;

/**
 * 转置矩阵
 * [数组]
 * 
 * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
 * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 * 示例 1：
 *      输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *      输出：[[1,4,7],[2,5,8],[3,6,9]]
 * 示例 2：
 *      输入：matrix = [[1,2,3],[4,5,6]]
 *      输出：[[1,4],[2,5],[3,6]]
 * 提示：
 *      m == matrix.length
 *      n == matrix[i].length
 *      1 <= m, n <= 1000
 *      1 <= m * n <= 105
 *      -10^9 <= matrix[i][j] <= 10^9
 *
 * @author ywh
 * @since 25/02/2021
 */
public class LeetCode867 {

    /**
     * Time: O(m*n), Space: O(1)
     *
     * @param matrix
     * @return
     */
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ret = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[j][i] = matrix[i][j];
            }
        }
        return ret;
    }
}
