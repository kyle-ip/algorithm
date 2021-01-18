package com.ywh.problem.leetcode.easy;

/**
 * 找不同
 * [位操作] [哈希表]
 * 
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 * 示例 1：
 *      输入：s = "abcd", t = "abcde"
 *      输出："e"
 *      解释：'e' 是那个被添加的字母。
 * 示例 2：
 *      输入：s = "", t = "y"
 *      输出："y"
 * 示例 3：
 *      输入：s = "a", t = "aa"
 *      输出："a"
 * 示例 4：
 *      输入：s = "ae", t = "aea"
 *      输出："a"
 * 提示：
 *      0 <= s.length <= 1000
 *      t.length == s.length + 1
 *      s 和 t 只包含小写字母
 *
 * @author ywh
 * @since 2020/12/18/018
 */
public class LeetCode389 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        counter[t.charAt(t.length() - 1) - 'a']--;
        int i = 0;
        for (; i < 26 && counter[i] == 0; i++);
        return (char) (i + 'a');
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference2(String s, String t) {
        char ret = 0;
        for (char c : (s + t).toCharArray()) {
            ret ^= c;
        }
        return ret;
    }

}
