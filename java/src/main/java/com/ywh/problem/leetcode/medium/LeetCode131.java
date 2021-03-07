package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 分割回文串
 * [回溯]
 *
 * @author ywh
 * @since 03/11/2019
 */
public class LeetCode131 {

    /**
     * 每轮递归都固定 start、循环 end，判断从 start 到 end 是否为回文子串：
     * start        end
     * 0            start -> n - 1
     * end + 1      start -> n - 1
     *
     * 当 start 达到字符串长度 n 时，会形成一个回文分割，添加到结果集；
     * 此时需要回溯到上一个状态
     *
     * @param s
     * @param start
     * @param dp
     * @param ret
     * @param elem
     */
    private void partition(String s, int start, boolean[][] dp, List<List<String>> ret, LinkedList<String> elem) {
        if (start >= s.length()) {
            ret.add(new LinkedList<>(elem));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!dp[start][i]) {
                continue;
            }
            // 从 start 到 i 是回文串，添加到 elem，并递归判断该子串的后续部分。
            elem.add(s.substring(start, i + 1));

            // 比如 [a, b, a,] (c, a, c)
            //      s      e   e+1 ...
            partition(s, i + 1, dp, ret, elem);

            // 回溯，退递归。
            elem.removeLast();
        }
    }

    /**
     * 参考 {@link LeetCode647}
     *
     * Time: O(2^n), Space: O(n^2)
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ret;
        }
        int n = s.length();

        // d[i][j] 表示 i~j 的子串是否为回文串。
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = true;
                } else if (i + 1 == j) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
            }
        }
        // 至此 dp 数组填充完成，开始回溯分割回文串。
        partition(s, 0, dp, ret, new LinkedList<>());
        return ret;
    }
}
