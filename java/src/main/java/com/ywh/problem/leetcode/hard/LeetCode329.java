package com.ywh.problem.leetcode.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 矩阵中的最长递增路径
 * [深度优先搜索] [拓扑排序] [记忆化]
 * 
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 * 示例 1：
 *      输入：matrix = [
 *              [9,9,4],
 *              [6,6,8],
 *              [2,1,1]
 *           ]
 *      输出：4
 *       解释：最长递增路径为 [1, 2, 6, 9]。
 * 示例 2：
 *      输入：matrix = [
 *              [3,4,5],
 *              [3,2,6],
 *              [2,2,1]
 *           ]
 *      输出：4
 *      解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * 示例 3：
 *      输入：matrix = [[1]]
 *      输出：1
 * 提示：
 *      m == matrix.length
 *      n == matrix[i].length
 *      1 <= m, n <= 200
 *      0 <= matrix[i][j] <= 2^31 - 1
 *
 * @author ywh
 * @since 2021/3/9/009
 */
public class LeetCode329 {

    public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 记忆化 DFS
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, ret = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ret = Math.max(ret, dfs(matrix, i, j, new int[m][n]));
            }
        }
        return ret;
    }

    /**
     *
     * @param matrix
     * @param x
     * @param y
     * @param memo
     * @return
     */
    public int dfs(int[][] matrix, int x, int y, int[][] memo) {
        // memo 用于记录当前位置下的递增路径长度最大值，如果已经有值则直接返回。
        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        ++memo[x][y];
        int m = matrix.length, n = matrix[0].length;
        for (int[] dir : dirs) {
            int nextX = x + dir[0], nextY = y + dir[1];
            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && matrix[nextX][nextY] > matrix[x][y]) {
                memo[x][y] = Math.max(memo[x][y], dfs(matrix, nextX, nextY, memo) + 1);
            }
        }
        return memo[x][y];
    }


    /**
     * 拓扑排序 + 动态规划
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath2(int[][] matrix) {
        // 把矩阵视为图，outDegrees[i][j] 表示 matrix[i][j] 的出度（即从 matrix[i][j] 射出、指向比 matrix[i][j] 值更大的点的边数）。
        int m = matrix.length, n = matrix[0].length;
        int[][] outDegrees = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int[] dir : dirs) {
                    int nextX = i + dir[0], nextY = j + dir[1];
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && matrix[nextX][nextY] > matrix[i][j]) {
                        ++outDegrees[i][j];
                    }
                }
            }
        }

        // 队列，存放出度为 0 的点（递增路径的终点，递增长度即为 1）。
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (outDegrees[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // 每轮循环取出所有出度为 0 的点（终点），处理其相邻节点出度 -1。
        // 可能会使得该点被重新添加到队列中，用作下一轮循环。
        // 最终总轮数即为最长递增路径长度。
        int ret = 0;
        while (!queue.isEmpty()) {
            ++ret;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                // 取出一个出度为 0 的点（其四周必然有出度不为 0 的点）。
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int[] dir : dirs) {
                    int nextX = x + dir[0], nextY = y + dir[1];
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && matrix[nextX][nextY] < matrix[x][y]) {
                        --outDegrees[nextX][nextY];
                        if (outDegrees[nextX][nextY] == 0) {
                            queue.offer(new int[]{nextX, nextY});
                        }
                    }
                }
            }
        }
        return ret;
    }
}
