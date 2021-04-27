package com.ywh.problem.leetcode.medium;

/**
 * 最大连续 1 的个数 III
 * [双指针] [滑动窗口]
 * 
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * 示例 1：
 *      输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 *      输出：6
 *      解释：
 *      [1,1,1,0,0,1,1,1,1,1,1]
 *      从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 *      输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 *      输出：10
 *      解释：
 *      [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 *      从 0 翻转到 1，最长的子数组长度为 10。
 * 提示：
 *      1 <= A.length <= 20000
 *      0 <= K <= A.length
 *      A[i] 为 0 或 1
 * 
 * @author ywh
 * @since 19/02/2021
 */
public class LeetCode1004 {

    /**
     * 对于任意右端点 r，希望找到最小的左端点 l，使得 [left, right] 包含不超过 K 个 0。
     * 枚举所有右端点，将得到的区间长度取最大值即可。
     *
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param A
     * @param K
     * @return
     */
    public int longestOnes(int[] A, int K) {
        int n = A.length;
        int[] P = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            P[i] = P[i - 1] + (1 - A[i - 1]);
        }

        int ret = 0;
        for (int r = 0; r < n; ++r) {
            int l = binarySearch(P, P[r + 1] - K);
            ret = Math.max(ret, r - l + 1);
        }
        return ret;
    }

    /**
     *
     * @param P
     * @param target
     * @return
     */
    public int binarySearch(int[] P, int target) {
        int low = 0, high = P.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (P[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param A
     * @param K
     * @return
     */
    public int longestOnes1(int[] A, int K) {
        // 前缀和表示 0 的个数，lsum（l-1）、rsum（r）分别表示左前缀和、右前缀和，则目标找到 rsum - lsum <= K。
        int ret = 0;
        for (int r = 0, l = 0, lsum = 0, rsum = 0; r < A.length; ++r) {

            // 如果 A[r] 为 0，则 1-A[r] 为 1，累加到 rsum 上。
            rsum += 1 - A[r];

            // 找到 rsum - lsum <= K 的位置，表示可以将 l 与 r 之间的 0 变成 1，再计算长度。
            for (; rsum - lsum > K; l++) {
                lsum += 1 - A[l];
            }
            ret = Math.max(ret, r - l + 1);
        }
        return ret;
    }

}
