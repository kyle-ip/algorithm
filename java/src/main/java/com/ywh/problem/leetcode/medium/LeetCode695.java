package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.UnionFind;

import java.util.HashSet;
import java.util.Stack;

/**
 * 岛屿的最大面积
 * [DFS] [数组] [并查集]
 * 
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
 * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 * 示例 1:
 *      [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *       [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *       [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *       [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *       [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *       [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *       [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *       [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 *      对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 * 示例 2:
 *      [[0,0,0,0,0,0,0,0]]
 *      对于上面这个给定的矩阵, 返回 0。
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 * @author ywh
 * @since 2020/11/12/012
 */
public class LeetCode695 {

    /**
     * 四连通问题：已知二维平面上的一个点，求其上下左右四个方向的点。
     *
     * Time: O(R*C), Space(R*C)
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;

        // 以邻接表降维存储图的节点和边的关系。
        HashSet<Integer>[] graph = new HashSet[row * col];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new HashSet<>();
        }

        // 四个方向。
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // 遍历图中所有顶点，把顶点转换为岛屿的二维坐标。
        for (int v = 0; v < graph.length; v++) {
            // 一维映射到二维：i => (i / col, i % col)
            int x = v / col, y = v % col;
            if (grid[x][y] == 0) {
                continue;
            }

            // 处理四个方向。
            for (int d = 0; d < 4; d++) {
                int nextX = x + dirs[d][0], nextY = y + dirs[d][1];
                if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col || grid[nextX][nextY] == 0) {
                    continue;
                }
                // 二维映射到一维：(x, y) => x * col + y

                // 合法的岛屿，连接。
                int next = nextX * col + nextY;
                graph[v].add(next);
                graph[next].add(v);
            }
        }
        boolean[] visited = new boolean[graph.length];

        int ret = 0;
        // 遍历图
        for (int v = 0; v < graph.length; v++) {
            // 转化为二维坐标，只关注陆地和未访问过的点。
            if (grid[v / col][v % col] == 0 || visited[v]) {
                continue;
            }

            // 对岛屿（一个连通分量）进行深度优先遍历。
            visited[v] = true;
            int area = 1;
            Stack<Integer> stack = new Stack<>();
            stack.push(v);
            while (!stack.empty()) {
                int cur = stack.pop();
                for (int w : graph[cur]) {
                    if (!visited[w]) {
                        area += 1;
                        stack.push(w);
                        visited[w] = true;
                    }
                }
            }
            ret = Math.max(ret, area);
//            private int dfs(int v, boolean[] visited, HashSet<Integer>[] graph) {
//                visited[v] = true;
//                int ret = 1;
//                for (int w : graph[v]) {
//                    if (!visited[w]) {
//                        ret += dfs(w, visited, graph);
//                    }
//                }
//                return ret;
//            }
//             ret = Math.max(ret, dfs(v, visited, graph));
        }
        return ret;
    }


    /**
     * Flood Fill 算法
     * 不转换，直接使用二维坐标。
     *
     * Time: O(R*C), Space(R*C)
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length, ret = 0;
        boolean[][] visited = new boolean[row][col];
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == 0 || visited[r][c]) {
                    continue;
                }
                visited[r][c] = true;
                int tmp = 1;
                Stack<int[]> stack = new Stack<>();
                stack.push(new int[]{r, c});
                while (!stack.empty()) {
                    int[] cur = stack.pop();
                    int x = cur[0], y = cur[1];
                    for (int d = 0; d < 4; d++) {
                        int nextX = x + dirs[d][0], nextY = y + dirs[d][1];
                        if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col || grid[nextX][nextY] == 0 || visited[nextX][nextY]) {
                            continue;
                        }
                        stack.push(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                        tmp += 1;
                    }
                }
                ret = Math.max(ret, tmp);
            }
        }
        return ret;
    }

    /**
     * @param grid
     * @return
     */
    public int maxAreaOfIsland3(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int ret = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) {
                    continue;
                }
                ret = Math.max(ret, dfs(r, c, grid));
            }
        }
        return ret;
    }

    /**
     *
     * @param x
     * @param y
     * @param grid
     * @return
     */
    private int dfs(int x, int y, int[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
            return 0;
        }
        // 避免重复计算，grid 置为 -1。
        grid[x][y] = 0;
        return 1 +
            dfs(x - 1, y, grid) +
            dfs(x, y + 1, grid) +
            dfs(x + 1, y, grid) +
            dfs(x, y - 1, grid);
    }

    /**
     * @param x
     * @param y
     * @param grid
     * @return
     */
    private int dfs2(int x, int y, int[][] grid) {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        grid[x][y] = 0;
        int res = 1;
        for (int d = 0; d < 4; d++) {
            int nextX = x + dirs[d][0], nextY = y + dirs[d][1];
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length || grid[nextX][nextY] == 0) {
                continue;
            }
            res += dfs2(nextX, nextY, grid);
        }
        return res;
    }

    /**
     * 并查集解法
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland4(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        UnionFind uf = new UnionFind(row * col);

        // 遍历所有坐标，把所有岛屿合并到 uf。
        for (int v = 0; v < row * col; v++) {
            int x = v / col, y = v % col;
             if (grid[x][y] == 0) {
                 continue;
             }
            for (int d = 0; d < 4; d++) {
                int nextX = x + dirs[d][0], nextY = y + dirs[d][1], next = nextX * col + nextY;
                if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length || grid[nextX][nextY] == 0) {
                    continue;
                }
                uf.union(v, next);
            }
        }

        // 统计所有节点在 uf 中的根节点的大小，求最大值。
        int ret = 0;
        for (int v = 0; v < row * col; v++) {
            if (grid[v / col][v % col] == 1) {
                ret = Math.max(ret, uf.size(v));
            }
        }
        return ret;
    }
}
