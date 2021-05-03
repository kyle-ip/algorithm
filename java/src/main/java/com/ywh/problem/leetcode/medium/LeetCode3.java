package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 无重复字符的最长子串
 * [哈希表] [双指针] [字符串] [滑动窗口]
 *
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 * 示例 1：
 *      输入: s = "abcabcbb"
 *      输出: 3
 *      解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2：
 *      输入: s = "bbbbb"
 *      输出: 1
 *      解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3：
 *      输入: s = "pwwkew"
 *      输出: 3
 *      解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4：
 *      输入: s = ""
 *      输出: 0
 * 提示：
 *      0 <= s.length <= 5 * 10^4
 *      s 由英文字母、数字、符号和空格组成
 *
 * @author ywh
 * @since 2/21/2019
 */
public class LeetCode3 {

    /**
     * 滑动窗口
     * Time: O(n), Space: O(m), m 是字符集大小
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2N(String s) {
        // 哈希表，左右指针，结果值。
        boolean[] hash = new boolean[256];
        int ret = 0;
        for (int l = 0, r = 0; r < s.length(); r++) {
            // 如果右指针指向的值存在于 hash 中，表示字符已经出现重复，此时需要重置最长子串的统计：
            // 不断把左指针到“重复元素首次出现的位置”之间的值全部剔除，移动左指针到新的起始位置。
            for (; hash[s.charAt(r)]; hash[s.charAt(l++)] = false);

            // 此时左指针与右指针之间的字符都没有重复，计算最大长度，并缓存右指针指向的值。
            ret = Math.max(ret, r - l + 1);
            hash[s.charAt(r)] = true;
        }
        return ret;
    }

    /**
     * Time: O(n), Space: O(m), m 是字符集大小
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1N(String s) {
        // 哈希表，key 为 s 中的字符，value 为右指针所指元素上次出现的下标。
        int[] idx = new int[256];
        Arrays.fill(idx, -1);
        int max = 0, l = 0, r = 0;
        for (; r < s.length(); r++) {
            // 每轮更新左指针：取当前位置与“右指针所指字符上次出现的位置 + 1”的较大者，表示必要时重置最长子串的统计。
            // 即假设 r 现在所指的字符 c 此前上次出现的位置是 2，则把 l 定在 3，再与当前的 r 计算距离。
            l = Math.max(l, idx[s.charAt(r)] + 1);
            // 最大长度取“当前最大长度”与“左右指针相隔距离”中的较大者。
            max = Math.max(max, r - l + 1);
            idx[s.charAt(r)] = r;
        }
        return max;
    }
}
