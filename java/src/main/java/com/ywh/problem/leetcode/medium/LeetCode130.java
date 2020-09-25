package com.ywh.problem.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 被围绕的区域
 * [DFS] [BFS] [并查集]
 *
 * @author ywh
 * @since 2020/9/25/025
 */
public class LeetCode130 {

    /**
     * TODO 暂时未理解
     *
     * Time: O(m*n), Space: O(m*n)
     *
     * @param board
     */
    public void solveBFS(char[][] board) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int n = board.length;
        if (n == 0) {
            return;
        }
        int m = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
            }
            if (board[i][m - 1] == 'O') {
                queue.offer(new int[]{i, m - 1});
            }
        }
        for (int i = 1; i < m - 1; i++) {
            if (board[0][i] == 'O') {
                queue.offer(new int[]{0, i});
            }
            if (board[n - 1][i] == 'O') {
                queue.offer(new int[]{n - 1, i});
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            board[x][y] = 'A';
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx < 0 || my < 0 || mx >= n || my >= m || board[mx][my] != 'O') {
                    continue;
                }
                queue.offer(new int[]{mx, my});
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
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
        // 把边界 O 标记为 A（保护这些不可能被包围的 O，使之不会被改为 X）

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
