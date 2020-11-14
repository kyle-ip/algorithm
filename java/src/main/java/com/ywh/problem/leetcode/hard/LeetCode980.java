package com.ywh.problem.leetcode.hard;

import java.util.Arrays;

/**
 * 不同路径 III
 * [DFS] [回溯]
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
