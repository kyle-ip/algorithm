package com.ywh.problem.leetcode.medium;

/**
 * 替换后的最长重复字符
 * [双指针] [滑动窗口]
 *
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
 * 在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意：字符串长度 和 k 不会超过 104。
 * 示例 1：
 *      输入：s = "ABAB", k = 2
 *      输出：4
 *      解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 *      输入：s = "AABABBA", k = 1
 *      输出：4
 *      解释：
 *           将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 *           子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 * https://leetcode-cn.com/problems/longest-repeating-character-replacement/solution/ti-huan-hou-de-zui-chang-zhong-fu-zi-fu-eaacp/
 *
 * @author ywh
 * @since 2021/3/10/010
 */
public class LeetCode424 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int max = 0, l = 0, r = 0;
        int[] hash = new int[26];

        // 右指针向后移动，统计该字符出现次数，保存字符出现频率的最大值。
        for (; r < s.length(); r++) {
            max = Math.max(max, ++hash[s.charAt(r) - 'A']);

            // 当最大值加上 k，小于 l 和 r（窗口）的距离，
            // 表示即使把最多 max 个字符 c、再加上从其他字符替换为 c 的 k 个，仍然不足以填满从 l 到 r 的距离。
            // 因此需要从左边对窗口进行收缩，比如 k == 1：
            // A, A, B, A, B, B, A
            // l           r
            // max == 3，max + k == 4，r - l + 1 == 5
            // 此时 l 左移，同时 A 的计数 -1。
            if (max + k < r - l + 1) {
                hash[s.charAt(l) - 'A']--;
                l++;
            }
        }
        return r - l;
    }

}
