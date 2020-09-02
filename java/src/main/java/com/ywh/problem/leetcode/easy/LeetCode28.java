package com.ywh.problem.leetcode.easy;

/**
 * 实现 strstr（从 haystack 中寻找 needle）
 * [双指针] [字符串]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode28 {

    /**
     * BF 算法
     * Time: O((n-m+1)*m), Space: O(1)
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strstr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i < n - m; i++) {
            int j = 0, k = i;
            for (; j < m && k < n && haystack.charAt(k) == needle.charAt(j); j++, k++);
            if (j == m) {
                return i;
            }
        }
        return -1;
    }

}
