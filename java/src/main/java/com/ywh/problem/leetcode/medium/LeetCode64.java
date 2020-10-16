package com.ywh.problem.leetcode.medium;

/**
 * 最小路径和
 * [数组] [动态规划]
 *
 * @author ywh
 * @since 2/22/2019
 */
public class LeetCode64 {

    /**
     * Time: O(m*n), Space: O(m*n)
     *
     * @param a
     * @return
     */
    public int minSumOfPath(int[][] a) {

        // 省略入参判断

        int m = a.length, n = a[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = a[0][0];

        // 分别求出第一行和第一列的路径和
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + a[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + a[0][i];
        }

        // 遍历矩阵，从左边和上边的值中取较小者
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + a[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * Time: O(m*n), Space: O(n)
     *
     * @param a
     * @return
     */
    public int minSumOfPath1dArray(int[][] a) {

        // 省略入参判断
        int m = a.length, n = a[0].length;
        int[] dp = new int[n];
        dp[0] = a[0][0];

        // 求出第一行的路径和
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + a[0][j];
        }

        // 遍历矩阵
        for (int i = 1; i < m; i++) {

            // 把矩阵每行第一个元素累加到 dp 数组第一项
            dp[0] += a[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(
                    // 相当于二维数组时上边的值（未更新）
                    dp[j],
                    // 相当于二维数组时左边的值（已更新）
                    dp[j - 1]
                ) + a[i][j];
            }

        }

        // 输入矩阵：
        // 1    4   9   18
        // 9    5   8   12
        // 14   5   11  12
        // 22   13  15  12

        // 下表中的空格为 dp 的值
        // i\j   0    1    2     3
        //  0   [1]   [5]  [14]  [32]
        //  1   [10]  [x]

        // 当 i = 0 时，dp = {1, 5, 14, 32}
        // 当 i = 1、j = 1 时，dp[j] == 5、dp[j-1] == 10，表中 x 的值为两者中的较小者 + 5

        return dp[n - 1];
    }
}
