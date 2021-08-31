package com.ywh.problem.leetcode.medium;

/**
 * 螺旋矩阵 II
 * [数组]
 *
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * 示例：
 *      输入: 3
 *      输出:
 *          [
 *           [ 1, 2, 3 ],
 *           [ 8, 9, 4 ],
 *           [ 7, 6, 5 ]
 *          ]
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
        for (int l = 0, r = n - 1, t = 0, b = n - 1, cur = 0;;) {
            for (int i = l; i <= r; i++) {
                matrix[t][i] = ++cur;
            }
            if (++t > b) {
                break;
            }
            for (int i = t; i <= b; i++) {
                matrix[i][r] = ++cur;
            }
            if (--r < l) {
                break;
            }
            for (int i = r; i >= l; i--) {
                matrix[b][i] = ++cur;
            }
            if (--b < t) {
                break;
            }
            for (int i = b; i >= t; i--) {
                matrix[i][l] = ++cur;
            }
            if (++l > r) {
                break;
            }
        }
        return matrix;
    }
}