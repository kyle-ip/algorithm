package com.ywh.problem.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N 皇后
 * [回溯]
 *
 * 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例：
 *      输入：4
 *      输出：[
 *          [".Q..",  // 解法 1
 *           "...Q",
 *           "Q...",
 *           "..Q."],
 *          ["..Q.",  // 解法 2
 *           "Q...",
 *           "...Q",
 *           ".Q.."]
 *      ]
 *      解释: 4 皇后问题存在两个不同的解法。
 * 提示：
 *      皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 *
 * @author ywh
 * @since 04/01/2021
 */
public class LeetCode51 {

    /**
     *
     * @param row
     * @param ret
     * @param board
     * @param visited   访问记录，第一维表示列、主对角线、副对角线三个方向。
     */
    private List<List<String>> backtracking(int row, List<List<String>> ret, char[][] board, boolean[][] visited) {
        int n = board.length;
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (char[] r : board) {
                list.add(new String(r));
            }
            ret.add(list);
        } else {
            // 检查当前行的每列，是否与前面放置的皇后有冲突：
            for (int col = 0; col < n; col++) {
                // row - col + n：经过 (row, col) 的主对角线（右下 -> 左上）已被占用。
                // row + col：经过 (row, col) 的副对角线（左下 -> 右上）已被占用。
                //                 c==3
                //  r-c+n==5 + . . . .
                //           . + . . .
                //           . . + . - r+c==6
                //           . . . * .
                //           . . . . .
                if (!visited[0][col] && !visited[1][row - col + n] && !visited[2][row + col]) {
                    // 放置皇后、标记。
                    board[row][col] = 'Q';
                    visited[0][col] = visited[1][row - col + n] = visited[2][row + col] = true;
                    // 判断下一行。
                    backtracking(row + 1, ret, board, visited);
                    // 退递归。
                    visited[0][col] = visited[1][row - col + n] = visited[2][row + col] = false;
                    board[row][col] = '.';
                }
            }
        }
        return ret;
    }

    /**
     * 如果一个问题可以分解成若干个相似的步骤，并且在每个步骤中有多个候选值，就可以考虑使用回溯法进行求解。
     * （递归，枚举）
     *
     * Time: O(n!), Space: O(n^2)
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        // 初始化棋盘。
        char[][] board = new char[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(board[i], '.');
        }
        return backtracking(0, new ArrayList<>(), board, new boolean[3][2 * n]);
    }
}
