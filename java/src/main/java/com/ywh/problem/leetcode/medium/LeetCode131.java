package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文分割
 * [回溯]
 *
 * @author ywh
 * @since 03/11/2019
 */
public class LeetCode131 {

    /**
     * TODO 暂时未完全理解
     *
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
     * @param result
     * @param elem
     */
    private void partition(String s, int start, boolean[][] dp, List<List<String>> result, List<String> elem) {
        if (start >= s.length()) {
            result.add(new ArrayList<>(elem));
        } else {
            for (int end = start; end < s.length(); end++) {
                if (dp[start][end]) {
                    elem.add(s.substring(start, end + 1));
                    partition(s, end + 1, dp, result, elem);
                    elem.remove(elem.size() - 1);
                }
            }
        }
    }

    /**
     * d[i][j] 表示 i ~ j 的子串是否为回文串，见 {@link LeetCode647}
     *
     * Time: O(2^n), Space: O(n^2)
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        int n = s.length();
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
        partition(s, 0, dp, result, new ArrayList<>());
        return result;
    }
}
