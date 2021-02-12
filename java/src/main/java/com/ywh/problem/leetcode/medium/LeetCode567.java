package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 字符串的排列
 * [字符串] [滑动窗口] [双指针]
 * 
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * 示例 1：
 *      输入: s1 = "ab" s2 = "eidbaooo"
 *      输出: True
 *      解释: s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *      输入: s1= "ab" s2 = "eidboaoo"
 *      输出: False
 * 提示：
 *      输入的字符串只包含小写字母
 *      两个字符串的长度都在 [1, 10,000] 之间
 *
 * @author ywh
 * @since 12/02/2021
 */
public class LeetCode567 {

    /**
     * Time: O(n+m+∣Σ∣), Space: O(∣Σ∣)
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Time: O(n+m+∣Σ∣), Space: O(∣Σ∣)
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion1(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
        }
        int left = 0;
        for (int right = 0; right < m; ++right) {
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
            while (cnt[x] > 0) {
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == n) {
                return true;
            }
        }
        return false;
    }

    /**
     * Time: O(n+m+∣Σ∣), Space: O(∣Σ∣)
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion2(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
            ++cnt[s2.charAt(i) - 'a'];
        }
        int diff = 0;
        for (int c : cnt) {
            if (c != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            int x = s2.charAt(i) - 'a', y = s2.charAt(i - n) - 'a';
            if (x == y) {
                continue;
            }
            if (cnt[x] == 0) {
                ++diff;
            }
            ++cnt[x];
            if (cnt[x] == 0) {
                --diff;
            }
            if (cnt[y] == 0) {
                ++diff;
            }
            --cnt[y];
            if (cnt[y] == 0) {
                --diff;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }


}
