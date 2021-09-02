package com.ywh.problem.leetcode.hard;

/**
 * 通配符匹配
 * [字符串] [动态规划] [贪心] [回溯]
 * 
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *      '?' 可以匹配任何单个字符。
 *      '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * 说明:
 *      s 可能为空，且只包含从 a-z 的小写字母。
 *      p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 *      输入:
 *      s = "aa"
 *      p = "a"
 *      输出: false
 *      解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *      输入:
 *      s = "aa"
 *      p = "*"
 *      输出: true
 *      解释: '*' 可以匹配任意字符串。
 * 示例 3:
 *      输入:
 *      s = "cb"
 *      p = "?a"
 *      输出: false
 *      解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 *      输入:
 *      s = "adceb"
 *      p = "*a*b"
 *      输出: true
 *      解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 *      输入:
 *      s = "acdcb"
 *      p = "a*c?b"
 *      输出: false
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
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchDP2(String s, String p) {
        int m = s.length(), n = p.length();

        // dp[i][j] 表示字符串 s 的前 i 个字符和模式 p 的前 j 个字符是否匹配。
        // 如果 p[j] 是字母，则 s[i] 必须是相同的字母。
        //      dp[i][j] = dp[i-1][j-1] && s[i] == p[i]
        // 如果 p[j] 是 ?，则 s[i] 可以为任意字符。
        //      dp[i][j] = dp[i-1][j-1]
        // 如果 p[j] 是 *，则 s[i] 没有任何要求，但 * 可以匹配零或任意多个小写字母，所以有两种情况：
        //      使用（推进 j）或不使用（推进 i）这个 *。
        //      dp[i][j] = dp[i][j-1] || dp[i-1][j]
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 边界条件：
        //      dp[0][0] = true     表示两串为空时匹配成功。
        //      dp[i][0] = false    表示空模式无法匹配任何字符串
        //      dp[0][j]            表示当字符串为空，只有当模式 p 的前 j 个字符均为 * 时，dp[0][j] 才为真。
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) != '*') {
                break;
            }
            dp[0][i] = true;
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                switch (p.charAt(j - 1)) {
                    case '*':
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j]; break;
                    case '?':
                        dp[i][j] = dp[i - 1][j - 1]; break;
                    default:
                        dp[i][j] = dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1); break;
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
        int m = s.length(), n = p.length();
        // sBegin 和 pBegin 用于记录 j 遇到“*”的位置，便于回退、确认“*”需要匹配多少个字符。
        int i = 0, j = 0, sStart = -1, pStart = -1;
        while (i < m) {
            // 字符相同（或遇到 p 串遇到“?”），两个下标后移。
            if (j < n && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            }
            // 字符不相同，但 p 串遇到“*”，则 j 移到“*”后的字符、记录两个串开始模糊匹配的位置。
            else if (j < n && p.charAt(j) == '*') {
                sStart = i;
                pStart = ++j;
            }
            // 字符不相同，且正在执行模糊匹配，则 i、j 还原（此时“*”用于匹配一个字符，因此 i 后移）。
            else if (pStart > -1) {
                i = ++sStart;
                j = pStart;
            }
            // 字符不相同，且不是模糊匹配，直接返回错误。
            else {
                return false;
            }
        }

        // 如果 p 比 s 长，且 p 后面还有“*”，则下标继续向后移动。
        for (; j < n && p.charAt(j) == '*'; j++);

        // 如果下标能走到最后，表示“*”后没有其他字符，返回 true，否则 false。
        return j == n;
    }

}
