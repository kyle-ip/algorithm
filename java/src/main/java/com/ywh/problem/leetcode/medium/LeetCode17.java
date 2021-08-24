package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 电话号码的字母组合
 * [字符串] [回溯]
 * 
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例 1：
 *      输入：digits = "23"
 *      输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *      输入：digits = ""
 *      输出：[]
 * 示例 3：
 *      输入：digits = "2"
 *      输出：["a","b","c"]
 * 提示：
 *      0 <= digits.length <= 4
 *      digits[i] 是范围 ['2', '9'] 的一个数字。
 *
 * @author ywh
 * @since 15/12/2019
 */
public class LeetCode17 {

    /**
     * 2 ~ 9
     */
    private final String[] mapping = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private List<String> combinations(String digits, int idx, String str, List<String> ret) {
        if (idx == digits.length()) {
            ret.add(str);
        } else {
            // 取出当前位置数字对应的字母映射，循环处理每个字母。
            for (char c: mapping[digits.charAt(idx) - '2'].toCharArray()) {
                combinations(digits, idx + 1, str + c, ret);
            }
        }
        return ret;
    }

    /**
     * Time: O(4^n), Space: O(n)
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinationsRecursive(String digits) {
        return combinations(digits, 0, "", new ArrayList<>());
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
        LinkedList<String> ret = new LinkedList<>();
        ret.add("");
        for (int i = 0; i < digits.length(); i++) {
            // 获取当前数字对应的字母组合映射
            String chars = mapping[digits.charAt(i) - '2'];

            // 遍历结果集合，每次取出最后一个元素
            for (int j = 0; j < ret.size(); j++) {
                String str = ret.poll();

                // 遍历每个字母，
                for (int k = 0; k < chars.length(); k++) {
                    ret.add(str + chars.charAt(k));
                }
            }
        }
        return ret;
    }

}
