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
        boolean[][] d = new boolean[m + 1][n + 1];
        d[0][0] = true;
        for (int i = 1; i <= m; ++i) {
            d[i][0] = false;
        }
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                d[0][j] = d[0][j - 1];
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
                    d[i][j] = d[i][j - 1] || d[i - 1][j];
                } else {
                    d[i][j] = false;
                }
            }
        }
        return d[m][n];
    }

    /**
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
        int i = 0, j = 0, sBegin = -1, pBegin = -1;
        while (i < m) {
            // 字符相同（或遇到 p 串遇到“?”），两个下标后移。
            if (j < n && isEqual(s.charAt(i), p.charAt(j))) {
                ++i;
                ++j;
            }
            // 字符不相同，但 p 串遇到“*”，则 p 串下标后移、且记录开始模糊匹配的位置。
            else if (j < n && p.charAt(j) == '*') {
                ++j;
                sBegin = i;
                pBegin = j;
            }
            // 字符不相同，且正在执行模糊匹配，则 s 串、p 串下标重置。
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
