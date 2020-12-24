package com.ywh.problem.leetcode.medium;

/**
 * 矩阵置零
 * [数组]
 *
 * @author ywh
 * @since 27/10/2019
 */
public class LeetCode73 {

    /**
     * Time: O(m*n), Space: O(m+n)
     *
     * @param a
     */
    public void setZeroInMatrix(int[][] a) {
        if (a == null || a.length == 0 || a[0] == null || a[0].length == 0) {
            return;
        }
        int m = a.length, n = a[0].length;

        // 用于记录特定行和列是否为 0
        boolean[] rows = new boolean[m], cols = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    rows[i] = cols[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || cols[j]) {
                    a[i][j] = 0;
                }
            }
        }
    }

    /**
     * 注意区分元素本来就为 0（使行列置 0）还是受到影响、被置 0 的
     *
     * Time: O(m*n), Space: O(1)
     *
     * @param a
     */
    public void setZeroInMatrixO1(int[][] a) {
        int m = a.length, n = a[0].length;

        // 先给第 0 行、第 0 列打上待置 0 标记（最后再置 0，避免混淆）
        boolean row0 = false, col0 = false;
        for (int i = 0; i < m; i++) {
            if (a[i][0] == 0) {
                col0 = true;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (a[0][i] == 0) {
                row0 = true;
                break;
            }
        }

        // 再把第 i 行第 0 列、第 0 行第 j 列置零，同时可用作右下子矩阵的标记
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    a[i][0] = a[0][j] = 0;
                }
            }
        }

        // 以左边和上边为标记，逐个判断右下子矩阵元素（从 (1, 1) 开始）是否应该置 0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i][0] == 0 || a[0][j] == 0) {
                    a[i][j] = 0;
                }
            }
        }

        // 处理左边和上边
        for (int j = 0; j < n && row0; ++j) {
            a[0][j] = 0;
        }
        for (int i = 0; i < m && col0; ++i) {
            a[i][0] = 0;
        }
    }

}
