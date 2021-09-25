package com.ywh.problem.leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 岛屿数量
 * [深度优先搜索] [广度优先搜索]
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
     *
     * @param g
     * @param i
     * @param j
     */
    private void dfs(char[][] g, int i, int j) {
        int m = g.length, n = g[0].length;
//        if (i < 0 || i >= m || j < 0 || j >= n || g[i][j] != '1') {
//            return;
//        }
//        g[i][j] = '2';
//        dfs(g, i + 1, j);
//        dfs(g, i - 1, j);
//        dfs(g, i, j + 1);
//        dfs(g, i, j - 1);
        Deque<int[]> stack = new LinkedList<>();
        stack.push(new int[]{i, j});
        while (!stack.isEmpty()) {
            int[] p = stack.pop();
            if (p[0] < 0 || p[0] >= m || p[1] < 0 || p[1] >= n || g[p[0]][p[1]] == '0' || g[p[0]][p[1]] == '2') {
                continue;
            }
            g[p[0]][p[1]] = '2';
            stack.push(new int[]{p[0] + 1, p[1]});
            stack.push(new int[]{p[0] - 1, p[1]});
            stack.push(new int[]{p[0], p[1] + 1});
            stack.push(new int[]{p[0], p[1] - 1});
        }
    }

    /**
     * Time: O(m*n), Space: O(m*n)
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length, num = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 海水或已经遍历过的海岛跳过。
                if (grid[i][j] != '1') {
                    continue;
                }
                num++;
                dfs(grid, i, j);
            }
        }
        return num;
    }
}
