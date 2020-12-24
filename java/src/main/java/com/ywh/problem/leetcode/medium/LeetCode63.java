package com.ywh.problem.leetcode.medium;

/**
 * 不同路径 II
 * [数组] [动态规划]
 * 
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 示例 1：
 *      输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 *      输出：2
 *      解释：
 *          3x3 网格的正中间有一个障碍物。
 *          从左上角到右下角一共有 2 条不同的路径：
 *          1. 向右 -> 向右 -> 向下 -> 向下
 *          2. 向下 -> 向下 -> 向右 -> 向右
 * 示例 2：
 *      输入：obstacleGrid = [[0,1],[0,0]]
 *      输出：1
 * 提示：
 *      m == obstacleGrid.length
 *      n == obstacleGrid[i].length
 *      1 <= m, n <= 100
 *      obstacleGrid[i][j] 为 0 或 1
 *
 * @author ywh
 * @since 05/11/2019
 */
public class LeetCode63 {

    /**
     * 和 {@link LeetCode62} 类似，但要判断 grid[i][j] 是否为 1，如果为 1 表示无法到达这个位置，不统计。
     * Time: O(m*n), Space: O(m*n)
     *
     * @param grid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] == 1 ? 0 : dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j] == 1 ? 0 : dp[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
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
    public int uniquePathsWithObstaclesOn(int[][] grid) {
        int n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0] == 1? 0: 1;
        for (int[] line : grid) {
            // 注意此处要判断，如果当前行的第一位为 1，则到达这个位置的路径数量为 0，否则取上一次的值（上方的值）。
            dp[0] = line[0] == 1? 0: dp[0];
            for (int j = 1; j < n; j++) {
                dp[j] = line[j] == 1? 0: dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}
