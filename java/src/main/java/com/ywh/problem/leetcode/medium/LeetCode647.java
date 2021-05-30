package com.ywh.problem.leetcode.medium;

/**
 * 回文子串
 * [字符串] [动态规划]
 * 
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 示例 1：
 *      输入："abc"
 *      输出：3
 *      解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *      输入："aaa"
 *      输出：6
 *      解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * 提示：
 *      输入的字符串长度不会超过 1000 。
 *
 * @author ywh
 * @since 2/26/2019
 */
public class LeetCode647 {

    /**
     * 动态规划：两层循环，外循环 i 从右到左，内循环 j 从 i 的位置出发，从左到右
     * 使用二维数组 dp[i][j] 记录 i~j 是否为回文子串，由 i == j、i + 1 == j 的值推出所有值
     * Time: O(n^2), Space: O(n^2)
     *
     * @param s
     * @return
     */
    public int countPalindromicSubstringsDP(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0, length = s.length();

        // 二位数组，dp[i][j] 记录 i~j 是否为回文子串
        boolean[][] dp = new boolean[length][length];

        // 外循环 i 从右到左
        for (int i = length - 1; i >= 0; i--) {

            // 内循环 j 从 i 的位置出发，从左到右。
            for (int j = i; j < length; j++) {
                // 当 i == j，表示单个字符，则 i~j 为回文子串。
                if (i == j) {
                    dp[i][j] = true;
                }
                // 当 i、j 相邻且元素相等，则 i~j 为回文子串，否则不是。
                else if (i + 1 == j) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }
                // 其余情况则要求 i、j 元素相等，其中间部分也为回文子串，则 i~j 为回文子串。
                else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 求字符串 s 中以下标 l、r 为中心的回文子串数量
     *
     * @param s
     * @param l
     * @param r
     * @return
     */
    private int expand(String s, int l, int r) {
        int cnt = 0;
        for (; l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r); cnt++, r++, l--);
        return cnt;
    }

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param s
     * @return
     */
    public int countPalindromicSubstringsExpand(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 遍历字符串中的每个字符，并以当前字符为中心向两边扩展；
        // 每扩展 1 位表示发现一个以当前字符为中心的回文子串（奇数最小为单个字符），计数器+1；
        // 共有两种情况，以当前字符为中心（奇数回文串）或以当前字符与下一个字符为中心（偶数回文串）
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += expand(s, i, i + 1) + expand(s, i, i);
        }
        return count;
    }
}
