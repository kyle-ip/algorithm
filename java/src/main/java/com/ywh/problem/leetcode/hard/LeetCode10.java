package com.ywh.problem.leetcode.hard;

/**
 * 简易正则表达式匹配
 * [字符串] [动态规划]
 * 
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *      '.' 匹配任意单个字符
 *      '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 示例 1：
 *      输入：s = "aa" p = "a"
 *      输出：false
 *      解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *      输入：s = "aa" p = "a*"
 *      输出：true
 *      解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 *      输入：s = "ab" p = ".*"
 *      输出：true
 *      解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4：
 *      输入：s = "aab" p = "c*a*b"
 *      输出：true
 *      解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 *      输入：s = "mississippi" p = "mis*is*p*."
 *      输出：false
 * 提示：
 *      0 <= s.length <= 20
 *      0 <= p.length <= 30
 *      s 可能为空，且只包含从 a-z 的小写字母。
 *      p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *      保证每次出现字符 * 时，前面都匹配到有效的字符
 *
 * @author ywh
 * @since 17/08/2020
 */
public class LeetCode10 {

    private boolean matches(String s, String p, int i, int j) {
        return i != 0 && (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1));
    }

    /**
     * Time: O(m*n) Space: O(m*n)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();

        // dp[i][j] 表示字符串 s 的前 i 个字符和模式 p 的前 j 个字符是否匹配。
        // 如果 p[j] 是字母，则 s 中必须匹配一个相同的字母：
        //      dp[i][j] = dp[i-1][j-1] && s[i] == s[j]
        // 如果 p[j] 是 *，则可以对 p[j-1] 重复任意自然数次，对于 * 及其前面一个字符（a*）：
        //      即将 s 末尾的字符匹配后扔掉，该组合继续匹配；或不匹配字符，直接将该组合扔掉。
        //      dp[i][j] = dp[i-1][j] || dp[i][j-2] （当 s[i] == p[j-1]）
        //                 dp[i][j-2]               （当 s[i] != p[j-1]）
        //      设 A = s[i] == p[j-1], B = dp[i-1][j], C = dp[i][j-2]，则原式等价于：
        //      dp[i][j] = A ∩ (B ∪ C) ∪ (A' ∩ C)
        //               = (A ∩ B) ∪ (A ∩ C) ∪ (A' ∩ C)
        //               = (A ∩ B) ∪ (C ∩ (A ∪ A))
        //               = (A ∩ B) ∪ C
        //               = (s[i] == p[j-1] && dp[i-1][j]) || dp[i][j-2]
        // 如果 p[j] 是 .，则 s[i] 可以为任意字符，所以以上字符相等的情况可以整理为：
        //      matches(i, j) = i != 0 && (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1));

        // 由于下标从 0 开始，在此基础上坐标都要 -1。
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    // 是 *，则对于 * 及其前面的字符可以选择：
                    // 1. 将 s 末尾的字符与 p * 前的字符匹配（matches[i][j-1]）后扔掉（dp[i-1][j]），该组合继续匹配。
                    // 2. 直接将 p 的该组合扔掉（dp[i][j-2]），p 其后的字符继续匹配。
                    dp[i][j] = matches(s, p, i, j - 1) && dp[i - 1][j] || dp[i][j - 2];
                } else {
                    // 不是 *，则从上一状态转换、且当前字符要匹配。
                    dp[i][j] = matches(s, p, i, j) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    private boolean isEqual(char sc, char pc) {
        return sc == pc || pc == '.';
    }


    /**
     * Time: O(m*n) Space: O(m*n)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {

        // dp[i][j] 表示 s[0:i-1] 与 p[0:j-1] 是否匹配。
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s.length(); ++i) {
            for (int j = 1; j <= p.length(); ++j) {
                // j-1 的位置出现“*”
                // s: a b c d
                //          i
                // p: a b c * d
                //            j
                if (p.charAt(j - 1) == '*') {
                    // 使“*”前的“c”出现 0 次，则“a b c *” => “a b”。
                    // 即 s[0:i-1] 与 p[0:j-1] 是否匹配取决于 s[0:i-1] 与 p[0:j-3] 是否匹配。
                    dp[i][j] = dp[i][j - 2];

                    // s: a b [c] d             s: a b [c] d
                    //            i      或                i
                    // p: a b [.] * d           p: a b [c] * d
                    //              j                        j

                    if (i > 0 && isEqual(s.charAt(i - 1), p.charAt(j - 2))) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                    //        ↓
                    // p: a b c  *  [j]
                    //           ↓
                    // s: a b d [i]
                }
                // s: a b [c] d            s: a b [c] d
                //            i      或               i
                // p: a b [.] d            p: a b [c] d
                //            j                       j
                else {
                    if (i > 0 && isEqual(s.charAt(i - 1), p.charAt(j - 1))) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
