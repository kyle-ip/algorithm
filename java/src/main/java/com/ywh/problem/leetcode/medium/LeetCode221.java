//package com.ywh.problem.leetcode.medium;
//
///**
// * 0/1 矩阵中的最大正方形
// * [动态规划] [数组] [栈]
// *
// * @author ywh
// * @since 16/11/2019
// */
//public class LeetCode221 {
//
//    private int min(int a, int b, int c) {
//        return Math.max(Math.max(a, b), c);
//    }
//
//    /**
//     * 把求最大面积转化成求最大边长，其平方为最大面积
//     *
//     * @param matrix
//     * @return
//     */
//    public int maximalSquareDP(char[][] matrix) {
//        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
//        }
//
//        // 状态 d[i][j] 表示只包含字符 1 并且右下角在 (i, j) 的最大正方形对应的边长
//        int m = matrix.length, n = matrix[0].length;
//        int[][] d = new int[m][n];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i == 0 || j == 0 || matrix[i][j] == '0') {
//                    d[i][j] = matrix[i][j] == '1'? 1: 0;
//                } else {
//                    d[i][j] = min(d[i - 1][j - 1], d[[]])
//                }
//            }
//        }
//
//    }
//
//}
