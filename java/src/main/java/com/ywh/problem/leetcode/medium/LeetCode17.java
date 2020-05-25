package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 电话号码对应的字母组合
 * [字符串] [回溯]
 *
 * @author ywh
 * @since 15/12/2019
 */
public class LeetCode17 {

    /**
     * 2 ~ 9
     */
    private final String[] mapping = new String[]{
        "abc", "def", "ghi", "jkl",
        "mno", "pqrs", "tuv", "wxyz"
    };

    private void combinations(String digits, int idx, String str, List<String> result) {
        if (idx == digits.length()) {
            result.add(str);
            return;
        }
        // 取出当前位置数字对应的字母映射，循环处理每个字母
        String chars = mapping[digits.charAt(idx) - '2'];
        for (int i = 0; i < chars.length(); i++) {
            combinations(digits, idx + 1, str + chars.charAt(i), result);
        }
    }

    /**
     * Time: O(4^n), Space: O(n)
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinationsRecursive(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        combinations(digits, 0, "", result);
        return result;
    }

    /**
     * 暂时未理解
     *
     * Time: O(4^n), Space: O(4^n)
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinationsIterative(String digits) {

        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        LinkedList<String> result = new LinkedList<>();
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            // 获取当前数字对应的字母组合映射
            String chars = mapping[digits.charAt(i) - '2'];

            // 遍历结果集合，每次取出最后一个元素
            for (int j = 0; j < result.size(); j++) {
                String str = result.poll();

                // 遍历每个字母，
                for (int k = 0; k < chars.length(); k++) {
                    result.add(str + chars.charAt(k));
                }
            }
        }
        return result;
    }

}
