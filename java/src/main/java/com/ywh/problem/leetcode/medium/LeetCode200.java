package com.ywh.problem.leetcode.medium;

import java.util.Stack;

/**
 * 小岛数量
 * [DFS] [BFS]
 *
 * @author ywh
 * @since 11/11/2019
 */
public class LeetCode200 {

    /**
     * 深度优先遍历（递归）
     *
     * @param g
     * @param visited
     * @param i
     * @param j
     */
    private void dfsRecursive(char[][] g, boolean[][] visited, int i, int j) {
        int m = g.length, n = g[0].length;
        // 越界、海水、已遍历返回
        if (i < 0 || i >= m || j < 0 || j >= n || g[i][j] == '0' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;

        // 把指定坐标接壤的周边区域都置为 true
        dfsRecursive(g, visited, i - 1, j);
        dfsRecursive(g, visited, i + 1, j);
        dfsRecursive(g, visited, i, j - 1);
        dfsRecursive(g, visited, i, j + 1);
    }

    /**
     * 深度优先遍历（迭代）
     *
     * @param g
     * @param visited
     * @param i
     * @param j
     */
    private void dfsIterative(char[][] g, boolean[][] visited, int i , int j) {
        int m = g.length, n = g[0].length;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});
        while(!stack.isEmpty()) {
            int[] p = stack.pop();
            if (p[0] < 0 || p[0] >= m || p[1] < 0 || p[1] >= n || g[p[0]][p[1]] == '0' || visited[p[0]][p[1]]) {
                continue;
            }
            visited[p[0]][p[1]] = true;
            stack.push(new int[]{p[0] - 1, p[1]});
            stack.push(new int[]{p[0] + 1, p[1]});
            stack.push(new int[]{p[0], p[1] - 1});
            stack.push(new int[]{p[0], p[1] + 1});
        }
    }

    /**
     * Time: O(m*n), Space: O(m*n)
     *
     * @param grid
     * @return
     */
    public int numberOfIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length, num = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 海水或已经遍历过的海岛跳过
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }
                num++;
                dfsRecursive(grid, visited, i, j);
            }
        }
        return num;
    }

}
