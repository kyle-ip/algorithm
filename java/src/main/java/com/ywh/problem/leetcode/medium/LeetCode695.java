package com.ywh.problem.leetcode.medium;

import java.util.HashSet;

/**
 * 岛屿的最大面积
 * [DFS] [数组]
 *
 * @author ywh
 * @since 2020/11/12/012
 */
public class LeetCode695 {

    /**
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int ret = 0;
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return ret;
        }
        int row = grid.length, col = grid[0].length;
        HashSet<Integer>[] graph = new HashSet[row * col];
        for (int i = 0; i < grid.length; i++) {
            graph[i] = new HashSet<>();
        }

        // 四个方向。
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // 逐行遍历二维数组 grid，以岛屿（grid 上为值 1 的点）为顶点建图。
        for (int v = 0; v < grid.length; v++) {
            int x = v / col, y = v % col;
            if (grid[x][y] == 0) {
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int nextX = x + dirs[d][0], nextY = y + dirs[d][1];
                if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) {
                    int next = nextX * col + nextY;
                    graph[v].add(next);
                    graph[next].add(v);
                }
            }
        }
        boolean[] visited = new boolean[graph.length];
        for (int v = 0; v < graph.length; v++) {
            int x = v / col, y = v % col;
            if (grid[x][y] == 1 && !visited[v]) {
                ret = Math.max(ret, dfs(v, visited, graph));
            }
        }
         return ret;
    }

    /**
     *
     * @param v
     * @param visited
     * @param graph
     * @return
     */
    private int dfs(int v, boolean[] visited, HashSet<Integer>[] graph) {
        visited[v] = true;
        int ret = 1;
        for (int w : graph[v]) {
            if (!visited[w]) {
                ret += dfs(w, visited, graph);
            }
        }
        return ret;
    }
}
