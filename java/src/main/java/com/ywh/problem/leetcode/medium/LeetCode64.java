package com.ywh.problem.leetcode.medium;

/**
 * 最小路径和
 * [数组] [动态规划]
 *
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 实例 1：
 *      [1] [3] [1]
 *      [1] [5] [1]
 *      [4] [2] [1]
 *      输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 *      输出：7
 *      解释：因为路径 1→3→1→1→1 的总和最小。
 * 实例 2：
 *      输入：grid = [[1,2,3],[4,5,6]]
 *      输出：12
 * 提示：
 *      m == grid.length
 *      n == grid[i].length
 *      1 <= m, n <= 200
 *      0 <= grid[i][j] <= 100
 *
 * @author ywh
 * @since 2/22/2019
 */
public class LeetCode64 {

    /**
     * Time: O(m*n), Space: O(m*n)
     *
     * @param grid
     * @return
     */
    public int minSumOfPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        // 分别求出第一行和第一列的路径和。
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        // 遍历矩阵，从左边和上边的值中取较小者。
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * Time: O(m*n), Space: O(n)
     *
     * @param grid
     * @return
     */
    public int minSumOfPath1dArray(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];

        // 求出第一行的路径和。
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            // 更新第一列路径和。
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                // 即二维数组时上边的值（未更新）、左边的值（已更新），加上当前位置的值。
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }

        }

        // 输入矩阵：
        // 1    4   9   18
        // 9   [5]  8   12
        // 14   5   11  12
        // 22   13  15  12

        // dp
        // i\j   0    1    2     3
        //  0   [1]   [5]  [14]  [32]   dp = {1, 5, 14, 32}
        //  1   [10]  [x]               dp[j] == 5、dp[j - 1] == 10，表中 x 的值为两者中的较小者 +5。

        return dp[n - 1];
    }
}
