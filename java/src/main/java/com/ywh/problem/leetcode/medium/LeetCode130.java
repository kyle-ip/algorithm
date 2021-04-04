package com.ywh.problem.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 被围绕的区域
 * [深度优先搜索] [广度优先搜索] [并查集]
 * 
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 示例:
 *      X X X X
 *      X O O X
 *      X X O X
 *      X O X X
 *      运行你的函数后，矩阵变为：
 *      X X X X
 *      X X X X
 *      X X X X
 *      X O X X
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，
 * 或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * @author ywh
 * @since 2020/9/25/025
 */
public class LeetCode130 {

    /**
     * Time: O(m*n), Space: O(m*n)
     *
     * @param board
     */
    public void solveBFS(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int m = board.length, n = board[0].length;
        Queue<int[]> queue=  new LinkedList<>();

        // 处理矩阵的左右边界，如果值为 O，则把坐标添加到队列中。
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                queue.add(new int[]{i, 0});
            }
            if (board[i][n - 1] == 'O') {
                queue.add(new int[]{i, n - 1});
            }
        }

        // 处理矩阵的上下边（注意不与上一步重复），如果值为 O，则把坐标添加到队列中。
        for (int j = 1; j < n - 1; j++) {
            if (board[0][j] == 'O') {
                queue.add(new int[]{0, j});
            }
            if (board[m - 1][j] == 'O') {
                queue.add(new int[] {m - 1, j});
            }
        }

        // 广度优先遍历，从队列中取出坐标，标记为 A保护不可能被包围的 O，使之在下一步不会被改为 X）。
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            board[x][y] = 'A';
            for (int[] dir : dirs) {
                int nextX = x + dir[0], nextY = y + dir[1];
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && board[nextX][nextY] == 'O') {
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    /**
     * @param board
     */
    public void solveDFS(char[][] board) {
        if (board.length == 0) {
            return;
        }
        // 递归处理，把边界或与边界相邻的、值为 O 的点标记为 A（保护不可能被包围的 O，使之不会被改为 X）。

        // 遍历处理左右边界的元素：
        // X X X X          X X X X
        // X O O X    =>    X O O X
        // X X O X          X X O X
        // X O X O          X O X A
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0);
            dfs(board, i, board[0].length - 1);
        }
        // 遍历处理上下边界的元素：
        // X X X X          X X X X
        // X O O X    =>    X O O X
        // X X O X          X X O X
        // X O X A          X A X A
        for (int i = 1; i < board[0].length - 1; i++) {
            dfs(board, 0, i);
            dfs(board, board.length - 1, i);
        }

        // 把 A 改为 O，把 O 改为 X：
        // X X X X          X X X X
        // X O O X    =>    X X X X
        // X X O X          X X X X
        // X O X A          X O X O
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    /**
     *
     * @param board
     * @param x
     * @param y
     */
    private void dfs(char[][] board, int x, int y) {
        // 标记过，或者本来就是 X，跳过。
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x - 1, y);
        dfs(board, x + 1, y);
        dfs(board, x, y - 1);
        dfs(board, x, y + 1);
    }
}
