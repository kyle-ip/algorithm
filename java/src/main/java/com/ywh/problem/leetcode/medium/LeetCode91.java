package com.ywh.problem.leetcode.medium;

/**
 * 解码方式
 * [字符串] [动态规划]
 *
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *      'A' -> 1
 *      'B' -> 2
 *      ...
 *      'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * 题目数据保证答案肯定是一个 32 位的整数。
 * 示例 1：
 *      输入：s = "12"
 *      输出：2
 *      解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 *      输入：s = "226"
 *      输出：3
 *      解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 *      输入：s = "0"
 *      输出：0
 * 示例 4：
 *      输入：s = "1"
 *      输出：1
 * 示例 5：
 *      输入：s = "2"
 *      输出：1
 * 提示：
 *      1 <= s.length <= 100
 *      s 只包含数字，并且可能包含前导零。
 *
 * @author ywh
 * @since 11/11/2019
 */
public class LeetCode91 {

    /**
     * 判断由 a、b 两个字符组成的两位数是否在 [10, 26] 范围内
     *
     * @param a
     * @param b
     * @return
     */
    private boolean isValidTwoDigit(char a, char b) {
        return (a == '1' && b >= '0' && b <= '9') || (a == '2' && b >= '0' && b <= '6');
    }

    /**
     * @param c
     * @param i
     * @return
     */
    private int decode(char[] c, int i) {
        // 正好用完所有字符，算一种。
        if (i == c.length) {
            return 1;
        }
        // 越界，忽略这种情况。
        if (i > c.length) {
            return 0;
        }

        // 解码方式数量。
        int num = 0;

        // 不为 0 的数字，解析为单个字符：a -> 1。
        if (c[i] != '0') {
            num += decode(c, i + 1);
        }
        // 连续的两个数字解析为单个字符：ab -> 12。
        if (i + 1 < c.length && isValidTwoDigit(c[i], c[i + 1])) {
            num += decode(c, i + 2);
        }
        return num;
    }

    /**
     * 递归解法
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public int numDecodingsRecursive(String s) {
        return decode(s.toCharArray(), 0);
    }

    /**
     * 动态规划解法
     *
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public int numDecodingsRecursiveDP(String s) {

        // d[i] 表示长度为 i 的子串的解码方式数量。
        int[] d = new int[s.length() + 1];

        // 注意 d[0] = 1，表示前 0 个字符的解码方式数量，所有的 d[i] 在最后一个字符或最后两个字符能有效解码时，
        // 就会取 d[i-1] 或 d[i-2] 的值，到达 d[0] 时形成有效的解码序列，需要初始化为 1。
        d[0] = 1;
        d[1] = s.charAt(0) != '0' ? 1 : 0;

        // d[i]：长度为 i，s.charAt(i - 1)：当前位置
        for (int i = 2; i <= s.length(); i++) {

            // 如果当前位置不为 0
            if (s.charAt(i - 1) != '0') {
                d[i] += d[i - 1];
            }
            // 如果当前位置与上一个位置的数字可解析为一个字符
            if (isValidTwoDigit(s.charAt(i - 2), s.charAt(i - 1))) {
                d[i] += d[i - 2];
            }

            // 添加到 d[i]，取决于此前的解码方式数量
        }
        return d[s.length()];
    }

    /**
     * 动态规划解法（优化存储空间）
     *
     * Time: O(n), Space: O(1)
     *
     * @param s
     * @return
     */
    public int numberOfDecodingsDPO1(String s) {
        //      [1]    [0]    [5]    [9]    [2]
        //      c1     c2     cur
        // c2：截至前一个位置的字符串的编码方式数量。
        // c1：截至前两个位置的字符串的编码方式数量。
        int c1 = 1, c2 = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= s.length(); ++i) {
            int cur = 0;

            //      [1]    [1]    [i]    [9]    [2]
            //     first  second third

            //      [1]    [0]    [i]    [9]    [2]
            //     first  second third
            if (s.charAt(i - 1) != '0') {
                cur += c2;
            }
            if (isValidTwoDigit(s.charAt(i - 2), s.charAt(i - 1))) {
                cur += c1;
            }
            c1 = c2;
            c2 = cur;
        }
        return c2;
    }
}
