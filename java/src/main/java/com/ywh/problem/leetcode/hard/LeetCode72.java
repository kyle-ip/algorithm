package com.ywh.problem.leetcode.hard;

/**
 * 编辑距离
 * [字符串] [动态规划] [回溯]
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 *      插入一个字符
 *      删除一个字符
 *      替换一个字符
 * 示例 1：
 *      输入：word1 = "horse", word2 = "ros"
 *      输出：3
 *      解释：
 *          horse -> rorse (将 'h' 替换为 'r')
 *          rorse -> rose (删除 'r')
 *          rose -> ros (删除 'e')
 * 示例 2：
 *      输入：word1 = "intention", word2 = "execution"
 *      输出：5
 *      解释：
 *          intention -> inention (删除 't')
 *          inention -> enention (将 'i' 替换为 'e')
 *          enention -> exention (将 'n' 替换为 'x')
 *          exention -> exection (将 'n' 替换为 'c')
 *          exection -> execution (插入 'u')
 * 提示：
 *      0 <= word1.length, word2.length <= 500
 *      word1 和 word2 由小写英文字母组成
 *
 * @author ywh
 * @since 2019/10/28
 */
public class LeetCode72 {

    /**
     * 回溯：在递归树中 (i, j) 两个变量重复的节点很多，因此只需要保留 edist 最小的，继续递归处理即可。
     *
     * @param s
     * @param i
     * @param t
     * @param j
     * @param edist
     * @param ret
     */
    public void backtracking(String s, int i, String t, int j, int edist, int[] ret) {
        // 结束条件：其中一个字符串下标已到达末尾，编辑距离需要加上另一个字符串剩余长度。
        // a b c
        //     i
        // a b c d e    dist += 2
        //     j
        if (i == s.length() || j == t.length()) {
            edist += j == t.length()? s.length() - i: t.length() - j;
            ret[0] = Math.min(ret[0], edist);
            return;
        }
        // 字符相同：两个字符串下标后移。
        if (s.charAt(i) == t.charAt(j)) {
            backtracking(s, i + 1, t, j + 1, edist, ret);
        }
        // 字符不同：编辑距离 + 1。
        else {
            // 删除 s[i] 或者 t[j] 前添加一个字符：s 的下标后移。
            backtracking(s, i + 1, t, j, edist + 1, ret);
            // 删除 t[j] 或者 s[i] 前添加一个字符：t 的下标后移。
            backtracking(s, i, t, j + 1, edist + 1, ret);
            // 将 s[i] 和 t[j] 替换为相同字符：两个字符串下标后移。
            backtracking(s, i + 1, t, j + 1, edist + 1, ret);
        }
    }

    /**
     * 回溯解法
     *
     * @return
     */
    public int editDistanceBacktracking(String s, String t) {
        int[] ret = {Integer.MAX_VALUE};
        backtracking(s, 0, t, 0, 0, ret);
        return ret[0];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * 动态规划解法
     *
     * 此处使用的编辑距离为莱文斯坦距离，允许增加、删除、替换字符操作，表示两个字符串差异的大小。
     * 另一种是最长公共子串长度，只允许增加、删除字符操作，表示两个字符串相似程度的大小。
     *
     * Time: O(m*n), Space: O(m*n)
     *
     * @param s
     * @param t
     * @return
     */
    public int editDistance(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        int m = s.length(), n = t.length();
        // dp[i][j] 表示长度为 i 的 s 的子串 -> 长度为 j 的 t 的子串的编辑距离。
        int[][] dp = new int[m + 1][n + 1];
        // s[0, i - 1] -> t[0, 0]
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        // s[0, 0] -> t[0, j - 1]
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // i == j == 3（两个子串长度都为 3），当前位置的字符 s[i - 1] 和 t[j - 1]。

                // 如果当前位置上的字符相同，则编辑距离取值与上次相同。
                // a b [b] -> a c [b] 与 a b -> a c 的编辑距离都为 1。
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 当前位置上的字符不相同。
                else {
                    // 从 s 到 t，共有三种变法：s 删去一个字符、t 删去一个字符、s 和 t 都删去一个字符，所以取三种变法的最小值 + 1。
                    // b a [b] -> c a [d]：
                    // b a -> c a       dp = 1  ✓  +1 = 2
                    // b a -> c a d     dp = 2
                    // b a b -> c a     dp = 2
                    dp[i][j] = 1 + min(dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Time: O(m*n), Space: O(n)
     *
     * @param s
     * @param t
     * @return
     */
    public int editDistance1dArray(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        int m = s.length() + 1, n = t.length() + 1;
        int[] dp = new int[n];
        for (int j = 0; j < n; ++j) {
            dp[j] = j;
        }
        for (int i = 1; i < m; ++i) {
            // 即解法 1 中的 dp[i - 1][j - 1]。
            int pre = dp[0];
            dp[0] = i;
            for (int j = 1; j < n; ++j) {
                int tmp = dp[j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = pre;
                } else {
                    dp[j] = min(pre, dp[j], dp[j - 1]) + 1;
                }
                pre = tmp;
            }
        }
        return dp[n - 1];
    }
}
