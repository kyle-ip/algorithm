package com.ywh.problem.leetcode.hard;

import java.util.Arrays;

/**
 * 简易正则表达式匹配
 * [字符串] [动态规划]
 *
 * @author ywh
 * @since 17/08/2020
 */
public class LeetCode10 {

    /**
     * Time: O(n^2) Space: O(n^2)
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        // dp[i][j] 表示 s 的前 i 个字符与 p 的前 j 个字符是否匹配（s 的前 0 个字符与 p 的前 0 个字符视为匹配）

        // dp[i][j] = dp[i - 1][j - 1] || dp[i]  (s[i] == p[j])
        // dp[i][j] = false              (s[i] != p[j])

        // if (p[j] == '*')
        // dp[i][j] = dp[i][j - 2]

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s.length(); ++i) {
            for (int j = 1; j <= p.length(); ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (i > 0 && j > 1 &&
                        (p.charAt(j - 2) == '.' || s.charAt(i - 1) == p.charAt(j - 2))) {
                        //        ↓
                        // p: a b c  *  [j]
                        //           ↓
                        // s: a b c [i]
                        dp[i][j] = dp[i][j]
                            //               ↓      （c 出现 0 次）
                            // p: a b c  *  [j]
                            //        ↓
                            // s: a b c [i]
                            || dp[i - 1][j];
                    }
                    //        ↓
                    // p: a b c  *  [j]
                    //           ↓
                    // s: a b d [i]
                }
                //        ↓                        ↓
                // p: a b c [j]     or      p: a b . [j]
                //        ↓                        ↓
                // s: a b c [i]             s: a b c [i]
                else {
                    if (i > 0 && (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1))) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
