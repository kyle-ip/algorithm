package com.ywh.problem.leetcode.medium;

import com.ywh.model.Point;

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
     * @param visit
     * @param i
     * @param j
     */
    private void dfsRecursive(char[][] g, boolean[][] visit, int i, int j) {
        int m = g.length, n = g[0].length;
        // 越界、海水、已遍历返回
        if (i < 0 || i >= m || j < 0 || j >= n || g[i][j] == '0' || visit[i][j]) {
            return;
        }
        visit[i][j] = true;

        // 把指定坐标接壤的周边区域都置为 true
        dfsRecursive(g, visit, i - 1, j);
        dfsRecursive(g, visit, i + 1, j);
        dfsRecursive(g, visit, i, j - 1);
        dfsRecursive(g, visit, i, j + 1);
    }

    /**
     * 深度优先遍历（迭代）
     *
     * @param g
     * @param visit
     * @param i
     * @param j
     */
    private void dfsIterative(char[][] g, boolean[][] visit, int i , int j) {
        int m = g.length, n = g[0].length;
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(i, j));
        while(!stack.isEmpty()) {
            Point p = stack.pop();
            if (p.x < 0 || p.x >= m || p.y < 0 || p.y >= n || g[p.x][p.y] == '0' || visit[p.x][p.y]) {
                continue;
            }
            visit[p.x][p.y] = true;
            stack.push(new Point(p.x - 1, p.y));
            stack.push(new Point(p.x + 1, p.y));
            stack.push(new Point(p.x, p.y - 1));
            stack.push(new Point(p.x, p.y + 1));
        }
    }

    /**
     * Time: O(m*n), Space: O(m*n)
     *
     * @param g
     * @return
     */
    public int numberOfIslands(char[][] g) {
        if (g == null || g.length == 0) {
            return 0;
        }
        int m = g.length, n = g[0].length, num = 0;
        boolean[][] visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 海水或已经遍历过的海岛跳过
                if (g[i][j] == '0' || visit[i][j]) {
                    continue;
                }
                num++;
                dfsRecursive(g, visit, i, j);
            }
        }
        return num;
    }

}
