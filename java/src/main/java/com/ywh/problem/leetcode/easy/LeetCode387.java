package com.ywh.problem.leetcode.easy;

import java.util.Arrays;

/**
 * 字符串中的第一个唯一字符
 * [字符串] [哈希表]
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * 示例：
 *      s = "leetcode"  返回 0
 *      s = "loveleetcode"  返回 2
 * 提示：你可以假定该字符串只包含小写字母。
 *
 * @author ywh
 * @since 3/15/2019
 */
public class LeetCode387 {

    /**
     * 遍历两次字符串，第一次统计字符出现的次数，第二次返回首个只出现一次字符。
     * Time: O(n), Space: O(m)
     *
     * @param s
     * @return
     */
    public int firstUniqCharTwoPass(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 遍历一次字符串，遍历字符串时统计字符出现的次数和字符首次出现时的下标。
     * 最后遍历下标数组，取最小下标返回即可。
     * Time: O(n), Space: O(m)
     *
     * @param s
     * @return
     */
    public int firstUniqCharOnePass(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }

        int[] count = new int[26], pos = new int[26];
        Arrays.fill(pos, -1);
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            count[idx]++;
            if (pos[idx] == -1) {
                pos[idx] = i;
            }
        }

        int idx = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (count[i] == 1 && pos[i] < idx) {
                idx = pos[i];
            }
        }
        return idx == Integer.MAX_VALUE ? -1 : idx;
    }
}
