package com.ywh.problem.leetcode.medium;

/**
 * 最长回文子串
 * 解法类似 647，具体解析参考 {@link LeetCode647}
 * <p>
 * [字符串] [动态规划]
 *
 * @author ywh
 * @since 2/28/2019
 */
public class LeetCode5 {

    /**
     * Time: O(n^2), Space: O(n^2)
     *
     * @param s
     * @return
     */
    public String longestPalindromeDP(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int n = s.length(), start = 0, maxLen = 0;
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

                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);

    }

    /**
     * 求以 left、right 为中心的回文子串最大长度
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private int expand(String s, int left, int right) {
        for (; left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right); left--, right++);
        return right - left + 1;
    }


    public String longestPalindromeExpand(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        // 记录最大回文子串的开始位置和长度
        int start = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = Math.max(expand(s, i, i), expand(s, i, i + 1));
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

}
