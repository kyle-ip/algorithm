package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 字符串解码
 * [栈] [深度优先搜索]
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 示例 1：
 *      输入：s = "3[a]2[bc]"
 *      输出："aaabcbc"
 * 示例 2：
 *      输入：s = "3[a2[c]]"
 *      输出："accaccacc"
 * 示例 3：
 *      输入：s = "2[abc]3[cd]ef"
 *      输出："abcabccdcdcdef"
 * 示例 4：
 *      输入：s = "abc3[cd]xyz"
 *      输出："abccdcdcdxyz"
 *
 * @author ywh
 * @since 2020/12/10/010
 */
public class LeetCode394 {

    /**
     * 栈解法
     *
     * Time: O(S), Space: O(S)
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        for (int p = 0; p < s.length(); ) {
            char cur = s.charAt(p);
            // 获取数字并进栈
            if (Character.isDigit(cur)) {
                StringBuilder ret = new StringBuilder();
                while (Character.isDigit(s.charAt(p))) {
                    ret.append(s.charAt(p++));
                }
                stack.add(ret.toString());
            }
            // 遇到字母 或 [，获取并入栈。
            else if (Character.isLetter(cur) || cur == '[') {
                stack.add(String.valueOf(s.charAt(p++)));
            }
            // 遇到 ]，处理一个子串。
            else {
                ++p;
                // ...3[abcd]...
                //      ↑  ↑
                LinkedList<String> substring = new LinkedList<>();
                while (!"[".equals(stack.peek())) {
                    substring.addFirst(stack.pop());
                }

                // 左括号出栈
                stack.pop();

                // ...3[abcd]...
                //    ↑x
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数。
                int repTime = Integer.parseInt(stack.pop());
                StringBuilder t = new StringBuilder();

                // 字符串列表转换成字符串：["a", "b", "c", "d"] => "abcd"
                String str = getString(substring);

                // 重复构造字符串："abcd" * 3 => "abcdabcdabcd"
                while (repTime-- > 0) {
                    t.append(str);
                }
                // 将构造好的字符串入栈
                stack.add(t.toString());
            }
        }
        return getString(stack);
    }

    /**
     *
     * @param v
     * @return
     */
    public String getString(List<String> v) {
        StringBuilder ret = new StringBuilder();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }


    /**
     * 从左到右解析字符串：
     * 如果当前位置为数字位，后面一定包含一个用方括号表示的字符串，即属于这种情况：k[...]
     *      此时可以先解析出一个数字，然后解析到了左括号，递归向下解析后面的内容，遇到对应的右括号就返回。
     *      此时可以根据解析出的数字 x 解析出的括号里的字符串 s' 构造出一个新的字符串 x * s'。
     *      把 k[...] 解析结束后，再次调用递归函数，解析右括号的内容。
     * 如果当前位置是字母位，可以直接解析当前这个字母，然后递归向下解析这个字母后面的内容。
     *
     * 根据题意可推导出巴科斯范式（BNF）：
     *      String -> Digits[String]String|Alpha String|ϵ
     *      Digits -> Digit Digits∣Digit
     *      Alpha  -> a|...|z|A|...Z
     *      Digit  -> 0|...|9
     * Digit 表示十进制数位，可能的取值是 00 到 99 之间的整数
     * Alpha 表示字母，可能的取值是大小写字母的集合，共 52 个
     * Digit 表示一个整数，它的组成是 Digit 出现一次或多次
     * String 代表一个待解析的字符串，它可能有三种构成，如 BNF 所示
     * ϵ 表示空串，即没有任何子字符
     *
     * 由于 Digits 和 Alpha 构成简单，很容易进行词法解析，可以把它看作独立的 token。
     * 此时的非终结符有 String，终结符 Digits、Alpha 和 ϵ，可以根据非终结符和 follow 集构造出预测分析表：
     *                      Alpha                           Digits                      ϵ
     * String       String -> Alpha String      String -> Digits[String]String      String -> ϵ
     *
     * 可见不含多重定义的项为 LL(1) 文法，即：
     *      从左向右分析
     *      最左推导
     *      超前查看一个符号
     *
     * Time: O(S), Space: O(s)
     *
     * @param ptr
     * @param src
     * @return
     */
    public String getString(int[] ptr, String src) {
        // String -> EPS
        if (ptr[0] == src.length() || src.charAt(ptr[0]) == ']') {
            return "";
        }

        char cur = src.charAt(ptr[0]);
        int repTime;
        StringBuilder ret = new StringBuilder();
        // String -> Digits [ String ] String
        if (Character.isDigit(cur)) {
            // 解析子串重复次数
            repTime = 0;
            while (ptr[0] < src.length() && Character.isDigit(src.charAt(ptr[0]))) {
                repTime = repTime * 10 + src.charAt(ptr[0]++) - '0';
            }
            // 过滤左括号 - 解析子串 - 过滤右括号，把子串重复指定次数后添加到结果集。
            ++ptr[0];
            String str = getString(ptr, src);
            ++ptr[0];
            while (repTime-- > 0) {
                ret.append(str);
            }
        }
        // String -> Char String
        else if (Character.isLetter(cur)) {
            ret = new StringBuilder(String.valueOf(src.charAt(ptr[0]++)));
        }
        return ret + getString(ptr, src);
    }

    /**
     *
     * @param s
     * @return
     */
    public String decodeString2(String s) {
        return getString(new int[1], s);
    }

}
