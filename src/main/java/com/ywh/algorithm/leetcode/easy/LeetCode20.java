package com.ywh.algorithm.leetcode.easy;

import java.util.Stack;

/**
 * 有效的括号序列
 * [字符串] [栈]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode20 {


    /**
     * 字符串逐位字符判断：
     * 左括号入栈，右括号则取栈顶元素比对是否匹配，匹配则弹出栈顶元素、判断下一位，否则返回错误；
     * 循环结束且栈空表示所有括号都匹配完成
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public boolean isValidBrackets(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            }
            if (stack.isEmpty()) {
                return false;
            } else {
                if (s.charAt(i) == ')' && stack.peek() != '('
                    || s.charAt(i) == ']' && stack.peek() != '['
                    || s.charAt(i) == '}' && stack.peek() != '{') {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }


    /**
     * 与方法1类似，遇到左括号时改为把左括号的对应的右括号入栈
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public boolean isValidBracketsShort(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push('[');
                    break;
                case '{':
                    stack.push('{');
                    break;
                default:
                    if (stack.isEmpty() || s.charAt(i) != stack.pop()) {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }
}
