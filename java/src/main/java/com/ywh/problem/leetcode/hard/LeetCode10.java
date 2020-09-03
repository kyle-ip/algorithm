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
     * 相等或匹配串的字符为 .
     *
     * @param sc
     * @param pc
     * @return
     */
    private boolean isEqual(char sc, char pc) {
        return sc == pc || pc == '.';
    }

    /**
     * Time: O(m*n), Space: O(m*n)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int m = s.length(), n = p.length();
        boolean[][] d = new boolean[m + 1][n + 1];

        // d[i][j] 表示前缀串 s(0, i-1) 和 p(0, j-1) 是否匹配，i 或 j 为 0 时表示空串
        d[0][0] = true;
        for (int i = 1; i <= m; ++i) {
            d[i][0] = false;
        }
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                d[0][j] = d[0][j - 2];
            } else {
                d[0][j] = false;
            }
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                char sc = s.charAt(i - 1), pc = p.charAt(j - 1);
                if (isEqual(sc, pc)) {
                    d[i][j] = d[i - 1][j - 1];
                } else if (pc == '*') {
                    char preChar = p.charAt(j - 2);
                    if (isEqual(sc, preChar)) {
                        d[i][j] = d[i][j - 2] || d[i][j - 1] || d[i - 1][j];
                    } else {
                        d[i][j] = d[i][j - 2];
                    }
                } else {
                    d[i][j] = false;
                }
            }
        }
        return d[m][n];
    }

    /**
     * Time: O(m*n), Space: O(m*n)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchShort(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length(), n = p.length();
        boolean[][] d = new boolean[m + 1][n + 1];


        d[0][0] = true;
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                d[0][j] = d[0][j - 2];
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                char sc = s.charAt(i - 1), pc = p.charAt(j - 1);
                if (isEqual(sc, pc)) {
                    d[i][j] = d[i - 1][j - 1];
                } else if (pc == '*') {
                    char preChar = p.charAt(j - 2);
                    if (isEqual(sc, preChar)) {
                        d[i][j] = d[i][j - 2] || d[i][j - 1] || d[i - 1][j];
                    } else {
                        d[i][j] = d[i][j - 2];
                    }
                } else {
                    d[i][j] = false;
                }
            }
        }
        return d[m][n];
    }

    /**
     * Time: O(m*n), Space: O(n)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchTwoArray(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length(), n = p.length();
        boolean[] pre = new boolean[n + 1];
        boolean[] cur = new boolean[n + 1];
        pre[0] = true;
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                pre[j] = pre[j - 2];
            }
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                char sc = s.charAt(i - 1), pc = p.charAt(j - 1);
                if (isEqual(sc, pc)) {
                    cur[j] = pre[j - 1];
                } else if (pc == '*') {
                    char preChar = p.charAt(j - 2);
                    if (isEqual(sc, preChar)) {
                        cur[j] = cur[j - 2] || cur[j - 1] || pre[j];
                    } else {
                        cur[j] = cur[j - 2];
                    }
                } else {
                    cur[j] = false;
                }
            }
            boolean[] tmp = cur;
            cur = pre;
            pre = tmp;
            Arrays.fill(cur, false);
        }
        return pre[n];
    }

    /**
     * Time: O(m*n), Space: O(n)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchOneArray(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length(), n = p.length();
        boolean[] cur = new boolean[n + 1];
        cur[0] = true;
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                cur[j] = cur[j - 2];
            }
        }

        for (int i = 1; i <= m; ++i) {
            boolean pre = cur[0];
            cur[0] = false;
            for (int j = 1; j <= n; ++j) {
                boolean tmp = cur[j];
                char sc = s.charAt(i - 1), pc = p.charAt(j - 1);
                if (isEqual(sc, pc)) {
                    cur[j] = pre;
                } else if (pc == '*') {
                    char preChar = p.charAt(j - 2);
                    if (isEqual(sc, preChar)) {
                        cur[j] = cur[j - 2] || cur[j - 1] || cur[j];
                    } else {
                        cur[j] = cur[j - 2];
                    }
                } else {
                    cur[j] = false;
                }
                pre = tmp;
            }
        }
        return cur[n];
    }

}
