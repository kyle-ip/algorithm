package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.easy.LeetCode191;

/**
 * 连续自然数二进制中 1 的个数
 * [动态规划] [位操作]
 *
 * @author ywh
 * @since 30/11/2019
 */
public class LeetCode338 {

    /**
     * 计算 n 这个数字中 1 的个数，参考 {@link LeetCode191}
     *
     * Time: O(k), Space: O(1)
     *
     * @param n
     * @return
     */
    private int numberOfOne(int n) {
        int count = 0;
        while (n != 0) {
            ++count;

            // 每次消除 n 最低位的 1，统计可消除的次数
            n &= (n - 1);
        }
        return count;
    }

    /**
     * Time: O(n*k), Space: O(1)
     *
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            d[i] = numberOfOne(i);
        }
        return d;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param n
     * @return
     */
    public int[] countBitsOn(int n) {
        int[] d = new int[n + 1];

        // 从 1 开始
        for (int i = 1; i <= n; i++) {

            // 由于 i & (i - 1) 消除了 i 最低位的 1，所以 i & (i - 1) 相比起 i 少了一个 1
            d[i] = d[i & (i - 1)] + 1;
        }

        return d;
    }

}
