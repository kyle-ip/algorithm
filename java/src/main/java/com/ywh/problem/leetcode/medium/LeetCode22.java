package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * [字符串] [回溯] [动态规划]
 * 
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 *      输入：n = 3
 *      输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *      输入：n = 1
 *      输出：["()"]
 * 提示：
 *      1 <= n <= 8
 *
 * @author ywh
 * @since 2/28/2019
 */
public class LeetCode22 {

    /**
     *
     * @param ret    保存所有合法排列的列表
     * @param str    当前排列
     * @param left   左括号剩余数量
     * @param right  右括号剩余数量
     */
    private List<String> generate(List<String> ret, String str, int left, int right) {
        if (left == 0 && right == 0) {
            ret.add(str);
        } else {
            if (left > 0) {
                generate(ret, str + "(", left - 1, right);
            }
            if (right > left) {
                generate(ret, str + ")", left, right - 1);
            }
        }
        return ret;
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
        return generate(new ArrayList<>(), "", n, n);
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
