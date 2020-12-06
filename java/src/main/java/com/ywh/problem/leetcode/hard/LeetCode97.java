package com.ywh.problem.leetcode.hard;

/**
 * 交错字符串
 * [字符串] [动态规划]
 *
 * @author ywh
 * @since 2020/10/23/023
 */
public class LeetCode97 {

    /**
     * @param s1
     * @param i
     * @param s2
     * @param j
     * @param s3
     * @param k
     * @param cache
     * @return
     */
    public static boolean backtracking(String s1, int i, String s2, int j, String s3, int k, int[][] cache) {
        if (cache[i][j] != 0) {
            return cache[i][j] == 1;
        }
        if (k == s3.length()) {
            return true;
        }
        boolean isValid = false;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            isValid = backtracking(s1, i + 1, s2, j, s3, k + 1, cache);
        }
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            isValid = isValid || backtracking(s1, i, s2, j + 1, s3, k + 1, cache);
        }
        cache[i][j] = isValid ? 1 : -1;
        return isValid;
    }

    /**
     * 回溯解法：
     *      s1 和 s2 的字符交错组成 s3，反过来说就是 s3 的每个字符都是由 s1 和 s2 组成。
     *      因此用三个指针记录 s1、s2、s3 的下标，如果允许组成，s3 当前字符必然取自 s1 和 s2 的当前字符。
     *      因此移动下标，递归求解剩余的字符串即可。
     *
     * Time: O(m*n), Space: O(m*n)
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleaveBacktracking(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int[][] cache = new int[s1.length() + 1][s2.length() + 1];
        return backtracking(s1, 0, s2, 0, s3, 0, cache);
    }

    /**
     * 动态规划解法
     *
     * Time: O(m*n), Space: O(m*n)
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t) {
            return false;
        }

        // dp[i][j] 表示 s1 的 第 i 个字符和 s2 的前 j 个字符能否交错组成 s3 的前 i+j 个字符。
        // 如果 s1[i] == s3[i+j]，则 dp[i][j] 取决于 dp[i-1][j]。
        // 同理 s2[j] == s3[i+j]，则 dp[i][j] 取决于 dp[i][j-1]。
        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 动态规划解法
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t) {
            return false;
        }
        boolean[] f = new boolean[m + 1];
        f[0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[j] = f[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    f[j] = f[j] || (f[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return f[m];
    }
}
