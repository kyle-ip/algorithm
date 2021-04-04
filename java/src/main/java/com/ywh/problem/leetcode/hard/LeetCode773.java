package com.ywh.problem.leetcode.hard;

import com.ywh.problem.leetcode.medium.LeetCode752;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 滑动谜题
 * [广度优先搜索]
 *
 * @author ywh
 * @since 2020/11/14
 */
public class LeetCode773 {

    /**
     * 类似打开转盘锁 {@link LeetCode752}，其状态转换：
     *      +------------> [[4, 0, 2],
     *      |              [5, 1, 3]]
     *      |
     * [[4, 1, 2], ------> [[4, 2, 2], ... ------> [[1, 2, 3],
     * [5, 0, 3]]          [0, 5, 3]]              [4, 5, 0]]
     *      |
     *      |              [[4, 1, 2],
     *      +------------> [5, 3, 0]]
     *
     * 排列问题，时间复杂度为阶乘。
     * Time: O(R*C*(R*C)!), Space: O(R*C*(R*C)!)
     *
     * @param board
     * @return
     */
    public int slidingPuzzle(int[][] board) {

        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        String initial = boardToString(board), target = "123450";
        if (initial.equals(target)) {
            return 0;
        }

        queue.add(initial);
        visited.put(initial, 0);
        while (!queue.isEmpty()) {
            String cur = queue.remove();
            int[][] curBoard = stringToBoard(cur);

            // 找到 0 的位置，转换为一维。
            int zero;
            for (zero = 0; zero < 6; zero++) {
                if (curBoard[zero / 3][zero % 3] == 0) {
                    break;
                }
            }

            // 把 0 的初始位置转换为二维。
            int zx = zero / 3, zy = zero % 3;
            for (int d = 0; d < dirs.length; d++) {
                int nextX = zx + dirs[d][0], nextY = zy + dirs[d][1];
                if (nextX < 0 || nextX >= 2 || nextY < 0 || nextY >= 3) {
                    continue;
                }
                swap(curBoard, zx, zy, nextX, nextY);
                String next = boardToString(curBoard);
                if (!visited.containsKey(next)) {
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);
                    if (next.equals(target)) {
                        return visited.get(next);
                    }
                }
                // 把状态换回来。
                swap(curBoard, zx, zy, nextX, nextY);
            }
        }
        return -1;
    }

    private void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int t = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = t;
    }

    /**
     * {{1, 2, 3}, {4, 5, 6}} => "123456"
     *
     * @param board
     * @return
     */
    private String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    /**
     * @param s
     * @return
     */
    private int[][] stringToBoard(String s) {
        int[][] board = new int[2][3];
        for (int i = 0; i < 6; i++) {
            board[i / 3][i % 3] = s.charAt(i) - '0';
        }
        return board;
    }

}
