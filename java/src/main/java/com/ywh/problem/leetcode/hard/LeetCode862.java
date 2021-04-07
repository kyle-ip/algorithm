package com.ywh.problem.leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 和至少为 K 的最短子数组
 * 
 * 返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
 * 如果没有和至少为 K 的非空子数组，返回 -1 。
 * 示例 1：
 *      输入：A = [1], K = 1
 *      输出：1
 * 示例 2：
 *      输入：A = [1,2], K = 4
 *      输出：-1
 * 示例 3：
 *      输入：A = [2,-1,2], K = 3
 *      输出：3
 * 提示：
 *      1 <= A.length <= 50000
 *      -10^5 <= A[i] <= 10^5
 *      1 <= K <= 10^9
 *
 * @author ywh
 * @since 4/7/2021
 */
public class LeetCode862 {

    /**
     *
     * @param A
     * @param K
     * @return
     */
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        long[] P = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            P[i + 1] = P[i] + (long) A[i];
        }

        // Want smallest y-x with P[y] - P[x] >= K
        int ans = n+1; // N+1 is impossible
        // opt(y) candidates, as indices of P
        Deque<Integer> monoq = new LinkedList<>();

        for (int y = 0; y < P.length; ++y) {
            // Want opt(y) = largest x with P[x] <= P[y] - K;
            while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()]) {
                monoq.removeLast();
            }
            while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + K) {
                ans = Math.min(ans, y - monoq.removeFirst());
            }
            monoq.addLast(y);
        }

        return ans < n + 1 ? ans : -1;
    }
}
