package com.ywh.problem.leetcode.hard;

/**
 * 编辑距离
 * [字符串] [动态规划]
 *
 * @author ywh
 * @since 2019/10/28
 */
public class LeetCode72 {

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * Time: O(m*n), Space: O(m*n)
     *
     * @param s
     * @param t
     * @return
     */
    public int editDistance(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        int m = s.length() + 1, n = t.length() + 1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // i - 1, j - 1 表示当前位置，如果当前位置上的字符相等，则编辑距离取值与上次相同
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * Time: O(m*n), Space: O(n)
     *
     * @param s
     * @param t
     * @return
     */
    public int editDistance1dArray(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }

        int m = s.length() + 1, n = t.length() + 1;
        int[] dp = new int[n];
        for (int j = 0; j < n; ++j)
            dp[j] = j;

        for (int i = 1; i < m; ++i) {

            // 相当于解法 1 中的 dp[i - 1][j - 1]
            int pre = dp[0];
            dp[0] = i;
            for (int j = 1; j < n; ++j) {
                int tmp = dp[j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = pre;
                } else {
                    dp[j] = min(pre, dp[j], dp[j - 1]) + 1;
                }
                pre = tmp;
            }
        }
        return dp[n - 1];
    }
}
