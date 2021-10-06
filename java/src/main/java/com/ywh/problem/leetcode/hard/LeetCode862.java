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
     * @param A
     * @param K
     * @return
     */
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;

        // 前缀和 p[i] 表示前 i 个元素之和。
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + (long) A[i];
        }

        // 找到 l 与 r，使得 P[r] - P[l] >= K，即 l 与 r 之间的和至少为 K。

        int ret = n + 1;
        // opt(y) candidates, as indices of P

        // 使用队列存放右边下标（模拟滑动窗口的右边界）
        Deque<Integer> deque = new LinkedList<>();

        for (int r = 0; r < prefixSum.length; ++r) {
            // 队列非空，且 r 的前缀和小于等于队尾值的前缀和，则移除队尾值。
            // 表示队尾和 r 之间的下标范围内的数有负值，移除。
            while (!deque.isEmpty() && prefixSum[r] <= prefixSum[deque.getLast()]) {
                deque.removeLast();
            }
            // 队列非空，且 r 的前缀和大于等于队首值的前缀和 + K，则更新结果值，并移除队首值。
            while (!deque.isEmpty() && prefixSum[r] >= prefixSum[deque.getFirst()] + K) {
                ret = Math.min(ret, r - deque.removeFirst());
            }
            deque.addLast(r);
        }

        return ret < n + 1 ? ret : -1;
    }
}
