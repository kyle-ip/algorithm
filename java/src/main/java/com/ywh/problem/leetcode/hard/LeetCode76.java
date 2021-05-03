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
        //

        // required 表示 s 中需求的字符统计值（初始化为 t 中字符出现的频度）。
        // 比如 t 为 aab，则 required['a'] == 2，required['b'] == 1。
        int[] required = new int[256];
        for (int i = 0; i < t.length(); ++i) {
            ++required[t.charAt(i)];
        }

        // start、len 用于截取返回结果子串，requiredCnt 表示剩余需要寻找的字符总量。
        int start = 0, len = s.length() + 1, requiredCnt = t.length();

        for (int l = 0, r = 0; r < s.length(); r++) {
            // required 中的统计值大于 0，表示 s.charAt(r) 即为 t 中的字符，总需求个数 -1、该字符需求个数 -1。
            if (required[s.charAt(r)] > 0) {
                requiredCnt--;
            }
            required[s.charAt(r)]--;

            // 每当 requiredCnt 减到 0，表示 [l、r] 包含的子串已覆盖 t。
            for (; requiredCnt == 0; l++) {
                // 找到更小的覆盖子串：记录起始位置、长度。
                if (r - l + 1 < len) {
                    start = l;
                    len = r - l + 1;
                }
                // 收缩左边界（左边统计值 +1，需要的字符总量 +1）。
                required[s.charAt(l)]++;
                if (required[s.charAt(l)] > 0) {
                    requiredCnt++;
                }
            }
        }
        return len > s.length() ? "" : s.substring(start, start + len);
    }

}
