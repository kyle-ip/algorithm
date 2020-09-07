package com.ywh.problem.leetcode.easy;

/**
 * 最长回文串的长度
 * [哈希表]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode409 {

    /**
     * Time: O(n), Space: O(m)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestPalindrome(String s) {
        int[] hash = new int[256];
        int oddCount = 0;

        // 利用 Hash 统计每个字符出现次数（Java 中基本类型的默认值是 0，引用类型会默认为 null）
        for (char c: s.toCharArray()) {
            hash[c]++;
        }

        // 统计出现奇数次的字符的个数（不重复）
        for (int count: hash) {
            if (count % 2 == 1) {
                oddCount++;
            }
        }

        // 如果出现奇数次的字符大于等于 1 个，则保留 1 个（放在中间），其余弃用消去
        return s.length() - Math.max(0, oddCount - 1);
    }
}
