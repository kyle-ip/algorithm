package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.easy.LeetCode53;

/**
 * 环形子数组的最大和
 * [数组]
 *
 * 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
 * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。
 * （形式上，当 0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 C[i+A.length] = C[i]）
 * 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。
 * （形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）
 * 示例 1：
 *      输入：[1,-2,3,-2]
 *      输出：3
 *      解释：从子数组 [3] 得到最大和 3
 * 示例 2：
 *      输入：[5,-3,5]
 *      输出：10
 *      解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * 示例 3：
 *      输入：[3,-1,2,-1]
 *      输出：4
 *      解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
 * 示例 4：
 *      输入：[3,-2,2,-3]
 *      输出：3
 *      解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * 示例 5：
 *      输入：[-2,-3,-1]
 *      输出：-1
 *      解释：从子数组 [-1] 得到最大和 -1
 * 提示：
 *      -30000 <= A[i] <= 30000
 *      1 <= A.length <= 30000
 *
 * @author ywh
 * @since 2021/3/23
 */
public class LeetCode918 {

    /**
     * Kadane 算法，参考 {@link LeetCode53}
     *
     * @param A
     * @return
     */
    public int maxSubarraySumCircular(int[] A) {
        int n = A.length;

        // 求最大子序和（Kadane 算法）。
        int ret = A[0], cur = A[0];
        for (int i = 1; i < n; ++i) {
            cur = A[i] + Math.max(cur, 0);
            ret = Math.max(ret, cur);
        }

        // 求从右到左的前缀和。
        int[] rightSums = new int[n];
        rightSums[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightSums[i] = rightSums[i + 1] + A[i];
        }

        // 求从右到左的最大和。
        int[] maxRight = new int[n];
        maxRight[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            maxRight[i] = Math.max(maxRight[i + 1], rightSums[i]);
        }

        // 去从左到右的前缀和，并更新结果。
        int leftsum = 0;
        for (int i = 0; i < n - 2; ++i) {
            leftsum += A[i];
            ret = Math.max(ret, leftsum + maxRight[i + 2]);
        }
        return ret;
    }
}
