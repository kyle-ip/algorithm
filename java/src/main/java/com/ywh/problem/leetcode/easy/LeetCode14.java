package com.ywh.problem.leetcode.easy;

/**
 * 字符串的最长公共前缀
 * [字符串]
 *
 * @author ywh
 * @since 19/11/2019
 */
public class LeetCode14 {

    /**
     * Time: O(k*n), Space: O(1)
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 以第一个字符串为基准，循环判断是否包含在其他字符串内
        String first = strs[0];
        for (int i = 0; i < first.length(); i++) {
            for (int j = 1; j < strs.length; j++) {

                // 取出第 i 个字符
                if (strs[j].length() <= i || strs[j].charAt(i) != first.charAt(i)) {
                    return first.substring(0, i);
                }
            }
        }
        return first;
    }

}
