package com.ywh.problem.leetcode.medium;

/**
 * 搜索单词
 * [数组] [回溯] [DFS]
 * 
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
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
     * 
     * @param board
     * @param visited
     * @param i
     * @param j
     * @param word
     * @param idx
     * @return
     */
    private boolean exist(char[][] board, boolean[][] visited, int i, int j, String word, int idx) {
        if (idx == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
            visited[i][j] || board[i][j] != word.charAt(idx)){
            return false;
        }

        visited[i][j] = true;
        boolean existed = exist(board, visited, i-1, j, word, idx+1)
            || exist(board, visited, i+1, j, word, idx+1)
            || exist(board, visited, i, j-1, word, idx+1)
            || exist(board, visited, i, j+1, word, idx+1);
        visited[i][j] = false;
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
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }

        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] != word.charAt(0)) {
                    continue;
                }
                if (exist(board, visited, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
}
