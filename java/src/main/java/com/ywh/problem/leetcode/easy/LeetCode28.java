package com.ywh.problem.leetcode.easy;

/**
 * 实现 strstr
 * [双指针] [字符串]
 * 
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 示例 1:
 *      输入: haystack = "hello", needle = "ll"
 *      输出: 2
 * 示例 2:
 *      输入: haystack = "aaaaa", needle = "bba"
 *      输出: -1
 * 说明:
 *      当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *      对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
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
