package com.ywh.problem.leetcode.hard;

import java.util.Stack;

/**
 * 有效括号的最大长度
 * [字符串] [动态规划] [栈]
 *
 * @author ywh
 * @since 18/11/2019
 */
public class LeetCode32 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public int maxLengthOfValidParenthesesStack2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 栈存储字符下标，弹出下标计算子串长度：子串最后字符的下标 - 子串前一个字符的下标（栈非空条件：栈非空且栈顶元素不为 -1）。
        // stack[0] = -1，左端的位置信息由当前不配对的括号下标提供：当子串最左边的括号下标为 0 时，它前一个字符的下标为 -1。
        int n = s.length(), max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.add(-1);
        for (int i = 0; i < n; i++) {
            // 栈顶的下标所指的字符是左括号且当前的下标所指的字符是右括号。
            if (!stack.isEmpty() && stack.peek() != -1 && s.charAt(stack.peek()) == '(' && s.charAt(i) == ')') {
                // 弹出栈顶的下标，表示完成一次左右括号匹配。
                stack.pop();
                // 当前右括号与上一个不配对位置的距离即为一对有效括号的长度，与当前较大值比较取较大者。
                // 如 ( ( )，则把第二个 ( 弹出，第一个 ( 与 ) 之间的距离为 2。
                // 如 ( )，第一个括号 ( 弹出，此时栈中保留的值 -1 派上用场：1 - (-1) == 2（-1 会一直存在于栈中，不能弹出）。
                max = Math.max(max, i - stack.peek());
            }
            // 栈空、与栈顶比较不配对都把下标入栈。
            else {
                stack.push(i);
            }
        }
        return max;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public int maxLengthOfValidParenthesesStack(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }


        int n = s.length(), top = 0, max = 0;
        int[] stack = new int[n + 1];
        stack[top] = -1;
        for (int i = 0; i < n; i++) {
            if (stack[top] != -1 && s.charAt(stack[top]) == '(' && s.charAt(i) == ')') {
                --top;
                max = Math.max(max, i - stack[top]);
            }
            else {
                stack[++top] = i;
            }
        }
        return max;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public int maxLengthOfValidParenthesesDP(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length(), left = 0, max = 0;
        int[] dp = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (left > 0) {
                left--;
                dp[i] = dp[i - 1] + 2;
                dp[i] += i >= dp[i]? dp[i - dp[i]]: 0;
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
