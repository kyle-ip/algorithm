package com.ywh.problem.leetcode.medium;

/**
 * 二叉搜索树的数量
 * [动态规划] [树] [数学]
 *
 * @author ywh
 * @since 07/11/2019
 */
public class LeetCode96 {

    /**
     * Time: O(n^2), Space: O(n)
     *
     * @param n
     * @return
     */
    public int numTreesDP(int n) {
        if (n < 0) {
            return 0;
        }

        // d[i] 表示第 i 个递增的数字能构成的二叉搜索树的数量
        int[] d = new int[n + 1];
        d[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                d[i] += d[j - 1] * d[i - j];
            }
        }
        return d[n];
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param n
     * @return
     */
    public int numTreesMath(int n) {
        if (n < 0) {
            return 0;
        }
        long result = 1;
        for (int k = 1; k <= n; ++k) {
            result = result * (n + k) / k;
        }
        return (int) (result / (n + 1));
    }
}
