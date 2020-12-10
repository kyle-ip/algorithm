package com.ywh.problem.leetcode.easy;

import java.util.Stack;

/**
 * 有效的括号
 * [字符串] [栈]
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 *      左括号必须用相同类型的右括号闭合。
 *      左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 示例 1：
 *      输入: "()"
 *      输出: true
 * 示例 2：
 *      输入: "()[]{}"
 *      输出: true
 * 示例 3：
 *      输入: "(]"
 *      输出: false
 * 示例 4：
 *      输入: "([)]"
 *      输出: false
 * 示例 5：
 *      输入: "{[]}"
 *      输出: true
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
            else {
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
        }
        return stack.isEmpty();
    }


    /**
     * 与方法 1 类似，遇到左括号时改为把左括号的对应的右括号入栈。
     * 使得判断字符串中的右括号时只需要与栈顶弹出的元素比较即可，精简判断。
     *
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
                    stack.push(']');
                    break;
                case '{':
                    stack.push('}');
                    break;
                default:
                    // 比如 { [ ] }，依次入栈的是 } ]。
                    // 当遇到 ] 时栈顶是 ]，因此匹配，否则返回 false。
                    if (stack.isEmpty() || s.charAt(i) != stack.pop()) {
                        return false;
                    }
            }
        }
        // 如果最终栈为空，表示所有的符号已经匹配并弹出。
        return stack.isEmpty();
    }

    public boolean isValidBrackets2(String s) {
        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            s = s.replace("()", "").replace("[]", "").replace("{}", "");
        }
        return "".equals(s);
    }
}
