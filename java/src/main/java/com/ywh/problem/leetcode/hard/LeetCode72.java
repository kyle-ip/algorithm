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

        // dp[i][j] 表示长度为 i 的 s 的子串 -> 长度为 j 的 t 的子串的编辑距离。
        int[][] dp = new int[m][n];

        // s[0, i - 1] -> t[0, 0]
        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }

        // s[0, 0] -> t[0, j - 1]
        for (int j = 0; j < n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // i == j == 3（两个子串长度都为 3），当前位置的字符 s[i - 1] 和 t[j - 1]。

                // 如果当前位置上的字符相同，则编辑距离取值与上次相同。
                // a b [b] -> a c [b] 与 a b -> a c 的编辑距离都为 1。
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 当前位置上的字符不相同。
                else {
                    // 从 s 到 t，共有三种变法：s 删去一个字符、t 删去一个字符、s 和 t 都删去一个字符，所以取三种变法的最小值 + 1。
                    // b a [b] -> c a [d]：
                    // b a -> c a       dp = 1  ✓  +1 = 2
                    // b a -> c a d     dp = 2
                    // b a b -> c a     dp = 2
                    dp[i][j] = 1 + min(dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]);
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
        for (int j = 0; j < n; ++j) {
            dp[j] = j;
        }
        for (int i = 1; i < m; ++i) {
            // 即解法 1 中的 dp[i - 1][j - 1]。
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
