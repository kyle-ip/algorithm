package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号的合法排列
 * [字符串] [回溯]
 *
 * @author ywh
 * @since 2/28/2019
 */
public class LeetCode22 {

    /**
     * 辅助递归方法：
     *
     * @param result 保存所有合法排列的列表
     * @param str    当前排列
     * @param left   左括号剩余数量
     * @param right  右括号剩余数量
     */
    private void generate(List<String> result, String str, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(str);
        } else {
            if (left > 0) {
                generate(result, str + "(", left - 1, right);
            }
            if (right > left) {
                generate(result, str + ")", left, right - 1);
            }
        }
    }

    /**
     * 递归解法
     *
     * Time: O(4^n / sqrt(n)), Space: O(n)      卡特兰数
     *
     * @param n
     * @return
     */
    public List<String> generateParentheses(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        generate(result, "", n, n);
        return result;
    }

    /**
     * 动态规划解法
     *
     * Time: O(4^n / n*sqrt(n)), Space: O(4^n / n*sqrt(n))
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesesDP(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        List<List<String>> dp = new ArrayList<>(n + 1);

        for (int i = 0; i < n + 1; i++) {
            dp.add(new ArrayList<>());
        }
        dp.get(0).add("");
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                for (String left : dp.get(j)) {
                    for (String right : dp.get(i - j - 1)) {
                        dp.get(i).add("(" + left + ")" + right);
                    }
                }
            }
        }

        return dp.get(n);
    }
}
