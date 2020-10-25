package com.ywh.problem.leetcode.hard;

/**
 * 简易正则表达式匹配
 * [字符串] [动态规划]
 *
 * @author ywh
 * @since 17/08/2020
 */
public class LeetCode10 {

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
