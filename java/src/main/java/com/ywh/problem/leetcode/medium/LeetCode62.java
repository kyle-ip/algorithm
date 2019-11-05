package com.ywh.problem.leetcode.medium;

/**
 * 路径数量
 * [数组] [动态规划]
 *
 * @author ywh
 * @since 05/11/2019
 */
public class LeetCode62 {

    /**
     * 动态规划，空间复杂度可优化为 O(min(m, n))，见 {@link LeetCode64}
     *
     * Time: O(m*n), Space: O(m*n)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsDP(int m, int n) {
        if (m < 1 || n < 1) {
            return 0;
        }
        int [][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 组合数学：
     * 在 m * n 的矩阵中从左上角走到右下角，都必须向下走 m - 1 步、向右走 n - 1 步，总步数 m + n - 2；
     * 可以从总步数 m + n - 1 中任意选 m - 1 步向下走，剩下向右走、或者任意选 n - 1 步向下走，剩下向右走；
     * 即 C(m - 1, m + n - 2) == C(n - 1, m + n - 2)
     * 其中组合数公式：C(b, a) == a!/(b!*(a-b)!) == a*(a-1)*...* (a-b+1)/b!
     *
     * Time: O(min(m, n)), Space: O(1)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsMath(int m, int n) {
        if (m < 1 || n < 1) {
            return 0;
        }
        int small = Math.max(m - 1, n - 1);
        int total = m + n - 2;

        // 避免乘法溢出
        long result = 1;

        // C(b, a) == a/1 * (a-1)/2 * ... (a-b+1)/b
        for (int i = 0; i < small; i++) {
            result = result * (total - i) / (i + 1);
        }
        return (int) result;
    }

}
