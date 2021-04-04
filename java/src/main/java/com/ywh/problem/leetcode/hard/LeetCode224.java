package com.ywh.problem.leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 基本计算器
 * [数学] [栈]
 *
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * 示例 1：
 *      输入：s = "1 + 1"
 *      输出：2
 * 示例 2：
 *      输入：s = " 2-1 + 2 "
 *      输出：3
 * 示例 3：
 *      输入：s = "(1+(4+5+2)-3)+(6+8)"
 *      输出：23
 * 提示：
 *      1 <= s.length <= 3 * 10^5
 *      s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 *      s 表示一个有效的表达式
 *
 * @author ywh
 * @since 2020/9/11/011
 */
public class LeetCode224 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        int sum = 0, op = 1, n = s.length();
        // 符号栈用于暂存一个子表达式值（括号内）的正负，子表达式结束后弹出。
        Deque<Integer> ops = new LinkedList<>();
        ops.push(1);
        for (int i = 0; i < n; ++i) {
            // 数字
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                for (; i + 1 < n && Character.isDigit(s.charAt(i + 1)); i++) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                }
                sum += ops.peek() * op * num;
            }
            // 左括号
            else if (s.charAt(i) == '(') {
                ops.push(ops.peek() * op);
                op = 1;
            }
            // 右括号
            else if (s.charAt(i) == ')') {
                ops.pop();
            }
            // 加号
            else if (s.charAt(i) == '+') {
                op = 1;
            }
            // 减号
            else if (s.charAt(i) == '-') {
                op = -1;
            }
        }
        return sum;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public int calculateTwoStack(String s) {
        Deque<Integer> numStack = new LinkedList<>();
        Deque<Character> opStack = new LinkedList<>();

        // 先添加一个左括号，用于处理整个表达式没有括号的情况。
        opStack.push('(');

        // 因为要对 i + 1 进行判断，所以要遍历到 s.length。
        for (int i = 0; i <= s.length(); i++) {
            // 遍历结束，或遇到右括号：一个子表达式结束。
            if (i == s.length() || s.charAt(i) == ')') {
                int sum = 0, num = numStack.pop();
                // 不断从操作符栈弹出元素，直到遇到左括号：从右到左，计算一个子表达式。
                while (opStack.peek() != '(') {
                    // - 可表示减号和负号，统一视为负号处理，因此一个子表达式内的所有操作都视为加。
                    if (opStack.pop() == '-') {
                        num = -num;
                    }
                    sum += num;
                    // 弹出下一个操作数。
                    num = numStack.pop();
                }
                sum += num;
                numStack.push(sum);
                // 最后把左括号弹出。
                opStack.pop();
            }
            // 数字拼接完整（当前位置为数字，且后续也是数字）后入栈。
            else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int num = s.charAt(i) - '0';
                while (i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                numStack.push(num);
            }
            // 加号、减号或左括号直接入操作符栈。
            else if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '(') {
                opStack.push(s.charAt(i));
            }
        }

        return numStack.peek();
    }
}
