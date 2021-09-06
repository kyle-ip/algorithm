package com.ywh.problem.leetcode.medium;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 丑数 II
 * [堆] [数学] [动态规划]
 * 
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * 示例 1：
 *      输入：n = 10
 *      输出：12
 *      解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 *      输入：n = 1
 *      输出：1
 *      解释：1 通常被视为丑数。
 * 提示：
 *      1 <= n <= 1690
 * 
 * @author ywh
 * @since 4/27/2021
 */
public class LeetCode264 {

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param n
     * @return
     */
    public int nthUglyNumberMinHeap(int n) {
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : new int[]{2, 3, 5}) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }

    /**
     *
     * @param n
     * @return
     */
    public int nthUglyNumberDP(int n) {

        // dp[i] 表示第 i 个丑数，
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2, p2 = 1, p3 = 1, p5 = 1; i <= n; i++) {
            // p2、p3、p5 分别表示上一个质因数为 2、3、5 的丑数的下标。

            // 取上一个丑数 num2、num3、num5，三者之间的最小者为 dp[i]。
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);

            // 被选取的其下标 +1。
            // 由于可能不止一个质因数（比如 10，其质因数为 2、5），因此不能用 else if。
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }
}
