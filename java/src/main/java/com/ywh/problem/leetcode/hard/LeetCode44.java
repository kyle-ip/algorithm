package com.ywh.problem.leetcode.hard;

/**
 * 通配符匹配
 * [字符串] [动态规划] [贪心] [回溯]
 *
 * @author ywh
 * @since 2020/10/23/023
 */
public class LeetCode44 {

    private boolean isEqual(char sc, char pc) {
        return sc == pc || pc == '?';
    }

    /**
     * 动态规划解法
     *
     * Time: O(m*n), Space: O(m*n)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchDP(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length(), n = p.length();
        // dp[i][j] 表示 s[0:i-1] 和 p[0:j-1] 是否匹配。
        boolean[][] dp = new boolean[m + 1][n + 1];
        // s[0:0] 与 p[0:0] 匹配。
        dp[0][0] = true;
        // s 与任何 p 空串都不匹配。
        for (int i = 1; i <= m; ++i) {
            dp[i][0] = false;
        }
        // s 空串与任何 p 都不匹配，但当 p 中的字符有“*”，则消除“*”，比如“a b *” => “a b”。
        // 因此 s[0:0] 与 p[0:j] 是否匹配取决于与 p[0:j-1] 是否匹配。
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            } else {
                dp[0][j] = false;
            }
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                char sc = s.charAt(i - 1), pc = p.charAt(j - 1);
                // s: a [b] c          s: a [b] c
                //          i                   i
                // p: a [b] c    或    p: a [.] c
                //          j                   j

                if (isEqual(sc, pc)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // s: a [b] c
                //          i
                // p: a [*] c
                //          j
                else if (pc == '*') {
                    // “*”匹配 0 个字符 || “*”匹配至少 1 个字符。
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 贪心解法
     *
     * Time: O(m*n), Space: O(1)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchGreedy(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length(), n = p.length();
        // sBegin 和 pBegin 用于记录 j 遇到“*”的位置，便于回退、确认“*”需要匹配多少个字符。
        int i = 0, j = 0, sBegin = -1, pBegin = -1;
        while (i < m) {
            // 字符相同（或遇到 p 串遇到“?”），两个下标后移。
            if (j < n && isEqual(s.charAt(i), p.charAt(j))) {
                ++i;
                ++j;
            }
            // 字符不相同，但 p 串遇到“*”，则 j 移到“*”后一个位置、记录两个串开始模糊匹配的位置。
            else if (j < n && p.charAt(j) == '*') {
                ++j;
                sBegin = i;
                pBegin = j;
            }
            // 字符不相同，且正在执行模糊匹配，则 i、j 还原（此时“*”用于匹配一个字符，因此 i 后移）。
            else if (pBegin != -1) {
                i = ++sBegin;
                j = pBegin;
            }
            // 字符不相同，且不是模糊匹配，直接返回错误。
            else {
                return false;
            }
        }

        // 对于 p 串比 s 串长的情况，如果 p 串后面还有“*”，则下标继续向后移动；如果下标能走到最后，表示“*”后没有其他字符，返回 true，否则 false。
        while (j < n && p.charAt(j) == '*') {
            ++j;
        }
        return j == n;
    }

}
