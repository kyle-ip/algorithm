package com.ywh.problem.leetcode.hard;

/**
 * 交错字符串
 * [字符串] [动态规划]
 * 
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 * 示例 1：
 *      输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 *      输出：true
 * 示例 2：
 *      输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 *      输出：false
 * 示例 3：
 *      输入：s1 = "", s2 = "", s3 = ""
 *      输出：true
 * 提示：
 *      0 <= s1.length, s2.length <= 100
 *      0 <= s3.length <= 200
 *      s1、s2、和 s3 都由小写英文字母组成
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
     * @param memo
     * @return
     */
    public static boolean backtracking(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j] == 1;
        }
        if (k == s3.length()) {
            return true;
        }
        boolean isValid = false;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            isValid = backtracking(s1, i + 1, s2, j, s3, k + 1, memo);
        }
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            isValid = isValid || backtracking(s1, i, s2, j + 1, s3, k + 1, memo);
        }
        memo[i][j] = isValid ? 1 : -1;
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
        return backtracking(s1, 0, s2, 0, s3, 0, new int[s1.length() + 1][s2.length() + 1]);
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
        int m = s1.length(), n = s2.length(), t = s3.length();
        if (m + n != t) {
            return false;
        }

        // dp[p1][p2] 表示 s1 的 第 p1 个字符和 s2 的前 p2 个字符能否交错组成 s3 的前 p1+p2 个字符。
        // 如果 s1[p1] == s3[p1+p2]，则 dp[p1][p2] 取决于 dp[p1-1][p2]。
        // 同理 s2[p2] == s3[p1+p2]，则 dp[p1][p2] 取决于 dp[p1][p2-1]。
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        for (int p1 = 0; p1 <= m; p1++) {
            for (int p2 = 0; p2 <= n; p2++) {
                int p3 = p1 + p2 - 1;
                if (p1 > 0) {
                    dp[p1][p2] = dp[p1][p2] || (dp[p1 - 1][p2] && s1.charAt(p1 - 1) == s3.charAt(p3));
                }
                if (p2 > 0) {
                    dp[p1][p2] = dp[p1][p2] || (dp[p1][p2 - 1] && s2.charAt(p2 - 1) == s3.charAt(p3));
                }
            }
        }
        return dp[m][n];
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
