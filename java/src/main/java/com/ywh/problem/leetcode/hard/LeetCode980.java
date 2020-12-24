package com.ywh.problem.leetcode.hard;

import java.util.Arrays;

/**
 * 不同路径 III
 * [DFS] [回溯]
 *
 * 在二维网格 grid 上，有 4 种类型的方格：
 *      1 表示起始方格。且只有一个起始方格。
 *      2 表示结束方格，且只有一个结束方格。
 *      0 表示我们可以走过的空方格。
 *      -1 表示我们无法跨越的障碍。
 * 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。
 * 每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。
 * 示例 1：
 *      输入：[
 *              [1,0,0,0],
 *              [0,0,0,0],
 *              [0,0,2,-1]
 *            ]
 *      输出：2
 *      解释：我们有以下两条路径：
 *              1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 *              2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * 示例 2：
 *      输入：[
 *              [1,0,0,0],
 *              [0,0,0,0],
 *              [0,0,0,2]
 *            ]
 *      输出：4
 *      解释：我们有以下四条路径：
 *          1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 *          2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 *          3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 *          4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * 示例 3：
 *      输入：[
 *              [0,1],
 *              [2,0]
 *            ]
 *      输出：0
 *      解释：
 *          没有一条路能完全穿过每一个空的方格一次。
 *          请注意，起始和结束方格可以位于网格中的任意位置。
 * 提示：
 *      1 <= grid.length * grid[0].length <= 20
 *
 * @author ywh
 * @since 14/11/2020
 */
public class LeetCode980 {

    /**
     * Time: O(n!) -> O(*2^n)
     *
     * @param grid
     * @return
     */
    public int uniquePathsIII(int[][] grid) {
        int row = grid.length, col = grid[0].length, left = row * col;
        int[][] memo = new int[1 << (row * col)][row * col];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        int start = -1, end = -1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    start = i * col + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == 2) {
                    end = i * col + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == -1) {
                    left--;
                }
            }
        }
        return dfs(start, 0, end, left, memo, grid);
    }

    /**
     *
     * @param v
     * @param visited
     * @param end
     * @param left
     * @param grid
     * @return
     */
    private int dfs(int v, int visited, int end, int left, int[][] memo, int[][] grid) {
        if (memo[visited][v] != -1) {
            return memo[visited][v];
        }
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        visited += (1 << v);
        left--;
        if (v == end && left == 0) {
            visited -= (1 << v);
            memo[visited][v] = 1;
            return 1;
        }
        int row = grid.length, col = grid[0].length, x = v / col, y = v % col;
        int res = 0;
        for (int d = 0; d < 4; d++) {
            int nextX = x + dirs[d][0], nextY = y + dirs[d][1];
            int next = nextX * col + nextY;
            if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col) {
                continue;
            }
            if (grid[nextX][nextY] == 0 && (visited & (1 << next)) == 0) {
                res += dfs(next, visited, end, left, memo, grid);
            }
        }
        visited -= (1 << v);
        memo[visited][v] = res;
        return res;
    }

}
