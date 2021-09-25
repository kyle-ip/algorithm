package com.ywh.problem.leetcode.medium;

/**
 * 搜索单词
 * [数组] [回溯] [深度优先搜索]
 *
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * 示例:
 *      board =
 *      [
 *        ['A','B','C','E'],
 *        ['S','F','C','S'],
 *        ['A','D','E','E']
 *      ]
 *      给定 word = "ABCCED", 返回 true
 *      给定 word = "SEE", 返回 true
 *      给定 word = "ABCB", 返回 false
 * 提示：
 *      board 和 word 中只包含大写和小写英文字母。
 *      1 <= board.length <= 200
 *      1 <= board[i].length <= 200
 *      1 <= word.length <= 10^3
 *
 * @author ywh
 * @since 3/15/2019
 */
public class LeetCode79 {

    /**
     * @param board
     * @param visited
     * @param x
     * @param y
     * @param word
     * @param idx
     * @return
     */
    private boolean exist(char[][] board, boolean[][] visited, int x, int y, String word, int idx) {
        if (idx == word.length()) {
            return true;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y] || board[x][y] != word.charAt(idx)) {
            return false;
        }
        visited[x][y] = true;
        boolean existed = exist(board, visited, x - 1, y, word, idx + 1)
            || exist(board, visited, x + 1, y, word, idx + 1)
            || exist(board, visited, x, y - 1, word, idx + 1)
            || exist(board, visited, x, y + 1, word, idx + 1);
        // 不同于统计岛屿数量，图上的每个点需要重复访问，因此每次回溯退递归都要把 visited 重置为 false。
        visited[x][y] = false;
        return existed;
    }

    /**
     * Time: O(m*n*3^k), Space: O(m*n)
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // 未访问且矩阵的字符为单词首字符：
                if (!visited[i][j] && board[i][j] == word.charAt(0) && exist(board, visited, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
}