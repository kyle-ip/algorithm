package com.ywh.problem.leetcode.medium;

import java.util.Stack;

/**
 * 岛屿数量
 * [DFS] [BFS]
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 示例 1：
 *      输入：grid = [
 *        ["1","1","1","1","0"],
 *        ["1","1","0","1","0"],
 *        ["1","1","0","0","0"],
 *        ["0","0","0","0","0"]
 *      ]
 *      输出：1
 * 示例 2：
 *      输入：grid = [
 *        ["1","1","0","0","0"],
 *        ["1","1","0","0","0"],
 *        ["0","0","1","0","0"],
 *        ["0","0","0","1","1"]
 *      ]
 *      输出：3
 * 提示：
 *      m == grid.length
 *      n == grid[i].length
 *      1 <= m, n <= 300
 *      grid[i][j] 的值为 '0' 或 '1'
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
        int m = grid.length, n = grid[0].length, num = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 海水或已经遍历过的海岛跳过。
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
