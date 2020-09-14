package com.ywh.problem.leetcode.medium;

/**
 * 螺旋矩阵 II
 *
 * @author ywh
 * @since 2020/9/12 10:42
 */
public class LeetCode59 {

    /**
     * Time: O(m*n), Space: O(1)
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            return null;
        }
        int [][]matrix = new int[n][n];
        int left = 0, right = n - 1, top = 0, bottom = n - 1, cur = 0;
        while (true) {
            for (int i = left; i <= right; i++) {
                matrix[top][i] = ++cur;
            }
            if (++top > bottom) {
                break;
            }
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = ++cur;
            }
            if (--right < left) {
                break;
            }
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = ++cur;
            }
            if (--bottom < top) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = ++cur;
            }
            if (++left > right) {
                break;
            }
        }
        return matrix;
    }
}