package com.ywh.problem.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断数独的有效性
 * [哈希表]
 *
 * @author ywh
 * @since 19/12/2019
 */
public class LeetCode36 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param board
     * @return
     */
    public boolean isValidSudokuBruteForce(char[][] board) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {

            // 判断纵列
            set.clear();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (!set.add(board[i][j])) {
                    return false;
                }
            }

            // 判断横排
            set.clear();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.') {
                    continue;
                }
                if (!set.add(board[j][i])) {
                    return false;
                }
            }

            // 处理小正方形（i 表示第 i + 1 个正方形）
            set.clear();
            for (int j = 0; j < 9; j++) {
                int r = 3 * (i / 3) + j / 3;
                int c = 3 * (i % 3) + j % 3;
                if (board[r][c] == '.') {
                    continue;
                }
                if (!set.add(board[r][c])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Time: O(n^2), Space: O(n^2)
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku2DArray(char[][] board) {

        // i、j、k 表示小正方形的编号，x 表示 1 ~ 9 的数字
        boolean[][] rowSeen = new boolean[9][9];
        boolean[][] colSeen = new boolean[9][9];
        boolean[][] boxSeen = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int x = board[i][j] - '1';
                int k = (i / 3) * 3 + j / 3;
                if (rowSeen[i][x] || colSeen[j][x] || boxSeen[k][x]) {
                    return false;
                }
                // 表示在第 i 行中，x 已经出现过
                rowSeen[i][x] = true;
                colSeen[j][x] = true;
                boxSeen[k][x] = true;
            }
        }
        return true;
    }
}
