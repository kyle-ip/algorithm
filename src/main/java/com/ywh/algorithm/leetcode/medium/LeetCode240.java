package com.ywh.algorithm.leetcode.medium;

/**
 * 行列递增的二维数组搜索
 * [二分搜索] [分治]
 *
 * @author ywh
 * @since 27/10/2019
 */
public class LeetCode240 {

    /**
     * Time: O(m+n), Space: O(1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public int[] searchIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[]{-1, -1};
        }
        int m = matrix.length, n = matrix[0].length;

        // 从右上角开始遍历
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return new int[]{i, j};
            }
            if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[]{-1, -1};
    }

}
