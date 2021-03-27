package com.ywh.problem.leetcode.hard;

/**
 * 最小覆盖子串
 * [哈希表] [双指针] [字符串] [滑动窗口]
 * 
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * 示例 1：
 *      输入：s = "ADOBECODEBANC", t = "ABC"
 *      输出："BANC"
 * 示例 2：
 *      输入：s = "a", t = "a"
 *      输出："a"
 * 提示：
 *      1 <= s.length, t.length <= 10^5
 *      s 和 t 由英文字母组成
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 *
 * @author ywh
 * @since 12/01/2020
 */
public class LeetCode76 {

    /**
     * Time: O(n), Space: O(m)
     *
     * @param s
     * @param t
     * @return
     */
    public String minSubstringContainT(String s, String t) {
        int[] required = new int[256];
        int start = 0, len = Integer.MAX_VALUE, requiredCnt = t.length();
        int left = 0, right = 0;
        for (int i = 0; i < t.length(); ++i) {
            ++required[t.charAt(i)];
        }

        for (; right < s.length(); ++right) {
            char r = s.charAt(right);
            if (required[r] > 0) {
                --requiredCnt;
            }
            --required[r];

            while (requiredCnt == 0) {
                if (right - left + 1 < len) {
                    start = left;
                    len = right - left + 1;
                }
                char l = s.charAt(left);
                ++required[l];
                if (required[l] > 0) {
                    ++requiredCnt;
                }
                ++left;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

}
