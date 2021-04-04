package com.ywh.problem.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二进制矩阵中的最短路径
 * [广度优先搜索]
 *
 * @author ywh
 * @since 2020/11/13/013
 */
public class LeetCode1091 {

    /**
     * Time: O(R*C), Space: O(R*C)
     *
     * @param grid
     * @return
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        if (grid[0][0] == 1) {
            return -1;
        }
        if (row == 1 && col == 1) {
            return 1;
        }
        // 已访问数组、距离数组、八方向、遍历队列。
        boolean[][] visited = new boolean[row][col];
        int[][] dis = new int[row][col];
        int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

        Queue<int[]> queue = new LinkedList<>();
        // Queue<Integer> queue = new LinkedList<>();
        // queue.add(0);
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        dis[0][0] = 1;

        while (!queue.isEmpty()) {
            // int cur = queue.poll(), x = cur / col, y = cur % col;
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            for (int[] dir : dirs) {
                int nextX = x + dir[0], nextY = y + dir[1];
                // 越界、已访问、禁止通行的坐标跳过。
                if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col || visited[nextX][nextY] || grid[nextX][nextY] == 1) {
                    continue;
                }
                // queue.add(nextX * col + nextY);
                queue.add(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
                dis[nextX][nextY] = dis[x][y] + 1;
                if (nextX == row - 1 && nextY == col - 1) {
                    return dis[nextX][nextY];
                }
            }
        }
        return -1;
    }

}
