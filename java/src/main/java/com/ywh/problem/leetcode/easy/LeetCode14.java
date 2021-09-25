package com.ywh.problem.leetcode.easy;

/**
 * 最长公共前缀
 * [字符串]
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1：
 *      输入：strs = ["flower","flow","flight"]
 *      输出："fl"
 * 示例 2：
 *      输入：strs = ["dog","racecar","car"]
 *      输出：""
 *      解释：输入不存在公共前缀。
 * 提示：
 *      1 <= strs.length <= 200
 *      0 <= strs[i].length <= 200
 *      strs[i] 仅由小写英文字母组成
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
        // 以第一个字符串为基准（最长公共前缀的长度不会超过它），循环判断是否包含在其他字符串内。
        String first = strs[0];

        // first 的每个字符。
        for (int i = 0; i < first.length(); i++) {

            // strs 中的其他字符串。
            for (int j = 1; j < strs.length; j++) {
                String s = strs[j];
                if (s.length() <= i || s.charAt(i) != first.charAt(i)) {
                    return first.substring(0, i);
                }
            }
        }
        return first;
    }

}
