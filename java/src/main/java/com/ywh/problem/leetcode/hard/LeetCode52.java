package com.ywh.problem.leetcode.hard;

/**
 * N 皇后 II
 * [回溯]
 * 
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * 示例:
 *      输入: 4
 *      输出: 2
 *      解释: 4 皇后问题存在如下两个不同的解法。
 *      [
 *          [".Q..",  // 解法 1
 *           "...Q",
 *           "Q...",
 *           "..Q."],
 *
 *          ["..Q.",  // 解法 2
 *           "Q...",
 *           "...Q",
 *           ".Q.."]
 *      ]
 * 提示：
 *      皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。
 *      当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。
 *
 * @author ywh
 * @since 04/01/2021
 */
public class LeetCode52 {

    /**
     *
     * @param row
     * @param n
     * @param ret
     * @param visited
     */
    private void solve(int row, int n, int[] ret, boolean[][] visited) {
        if (row == n) {
            ++ret[0];
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!visited[0][col] && !visited[1][row - col + n] && !visited[2][row + col]) {
                visited[0][col] = visited[1][row - col + n] = visited[2][row + col] = true;
                solve(row + 1, n, ret, visited);
                visited[0][col] = visited[1][row - col + n] = visited[2][row + col] = false;
            }
        }
    }

    /**
     * 参考 {@link LeetCode51}
     *
     * Time: O(n!), Space: O(n^2)
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        int[] ret = new int[]{0};
        boolean[][] visited = new boolean[3][2 * n];
        solve(0, n, ret, visited);
        return ret[0];
    }

}
