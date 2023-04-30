package com.ywh.problem.leetcode.medium;

/**
 * 移动石子直到连续
 * [数学]
 * 
 * 三枚石子放置在数轴上，位置分别为 a，b，c。
 * 每一回合，你可以从两端之一拿起一枚石子（位置最大或最小），并将其放入两端之间的任一空闲位置。
 * 形式上，假设这三枚石子当前分别位于位置 x, y, z 且 x < y < z。
 * 那么就可以从位置 x 或者是位置 z 拿起一枚石子，并将该石子移动到某一整数位置 k 处，其中 x < k < z 且 k != y。
 * 当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。
 * 要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves]
 * 
 * 
 * @author ywh
 * @since 4/2/2021
 */
public class LeetCode1033 {


    /**
     * Time: O(1), Space: O(1)
     * 
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int[] numMovesStones(int a, int b, int c) {
        int x = Math.min(a, Math.min(b, c)), z = Math.max(a, Math.max(b, c));
        int y = a + b + c - x - z;
        
        int[] res = new int[2];
        res[0] = 2;
        if (z - y == 1 && y - x == 1) {
            res[0] = 0;
        } else if (z - y <= 2 || y - x <= 2) {
            res[0] = 1;
        }
        res[1] = z - x - 2;
        return res;
    }
}
