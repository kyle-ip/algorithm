package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 最长回文子串
 * [字符串] [动态规划]
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 示例 1：
 *      输入：s = "babad"
 *      输出："bab"
 *      解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *      输入：s = "cbbd"
 *      输出："bb"
 * 示例 3：
 *      输入：s = "a"
 *      输出："a"
 * 示例 4：
 *      输入：s = "ac"
 *      输出："a"
 * 提示：
 *      1 <= s.length <= 1000
 *      s 仅由数字和英文字母（大写和/或小写）组成
 *
 * 解法类似 {@link LeetCode647}
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
        int n = s.length(), start = 0, maxLen = 0;

        // 二维数组 dp[i][j] 记录 i~j 是否为回文子串。
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {

                // b a b a d
                //         i
                //    <- l

                // 单个字符都是回文串：a
                if (i == j) {
                    dp[i][j] = true;
                }
                // 两个字符，相同即为回文串：a, a
                else if (i + 1 == j) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }
                // 两个以上的字符，只要边界相等且内部为回文串，整体即为回文串：a, [...], a，其中 [...] = b, b
                else {
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
     * @param l
     * @param r
     * @return
     */
    private int expand(String s, int l, int r) {
        for (; l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r); l--, r++) ;
        return (r - 1) - (l + 1) + 1;
    }

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param s
     * @return
     */
    public String longestPalindromeExpand(String s) {
        // 记录最大回文子串的开始位置和长度。
        int start = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            // 处理 a, b, b, a 和 a, b, a 两种回文串。
            int len = Math.max(expand(s, i, i), expand(s, i, i + 1));
            if (len > maxLen) {
                maxLen = len;

                // 0 1 2 3 3 2 1 5          偶数：
                //       i                  i = 3, len = 6, start = 1
                // 0 1 2 3 2 1 5            奇数：
                //       i                  i = 3, len = 5, start = 1
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * Manacher 算法
     *
     * Time: O(n), Space: O(1)
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int start = 0, end = -1;
        StringBuffer t = new StringBuffer("#");
        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        List<Integer> armLen = new ArrayList<>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); ++i) {
            int curArmLen;
            if (right >= i) {
                int iSym = j * 2 - i;
                int minArmLen = Math.min(armLen.get(iSym), right - i);
                curArmLen = expand2(s, i - minArmLen, i + minArmLen);
            } else {
                curArmLen = expand2(s, i, i);
            }
            armLen.add(curArmLen);
            if (i + curArmLen > right) {
                j = i;
                right = i + curArmLen;
            }
            if (curArmLen * 2 + 1 > end - start) {
                start = i - curArmLen;
                end = i + curArmLen;
            }
        }

        StringBuilder ret = new StringBuilder();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ret.append(s.charAt(i));
            }
        }
        return ret.toString();
    }

    public int expand2(String s, int l, int r) {
        for (; l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r); l--, r++) ;
        return (r - l - 2) / 2;
    }
}
