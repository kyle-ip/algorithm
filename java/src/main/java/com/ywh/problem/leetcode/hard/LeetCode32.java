package com.ywh.problem.leetcode.hard;

/**
 * 有效括号的最大长度
 * [字符串] [动态规划]
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
    public int maxLengthOfValidParenthesesStack(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 计算子串长度：子串最后字符的下标 - 子串前一个字符的下标；
        // stack[0] = -1，左端的位置信息由当前不配对的括号下标提供：当子串最左边的括号下标为 0 时，它前一个字符的下标为 -1
        int n = s.length(), p = -1, max = 0;
        int[] stack = new int[n + 1];
        stack[++p] = -1;

        // ) ( ( ) ( ) )
        for (int i = 0; i < n; i++) {
            // 栈非空、栈顶元素是左括号且当前元素是右括号
            if (stack[p] != -1 && s.charAt(stack[p]) == '(' && s.charAt(i) == ')') {
                // 弹出栈顶元素（相邻左括号）
                --p;
                // 当前右括号与上一个不配对位置的距离，与当前较大值比较，取较大者
                // 如 ( ( )，则把第二个 ( 弹出，第一个 ( 与 ) 之间的距离为 2
                max = Math.max(max, i - stack[p]);
            }
            // 每当栈空或与栈顶比较不配对都入栈：( (、) ( 等
            else {
                stack[++p] = i;
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

        // left 表示左括号的数量，d[i] 表示以第 i 个字符结尾的有效括号子串的最大长度
        // 状态数组 d 只有发生匹配时才会被填充，因此需要 max 值记录大小，而不是直接返回最后一项
        int n = s.length(), left = 0, max = 0;
        int[] d = new int[n + 1];

        for (int i = 0; i < n; i++) {
            // 当前为左括号：统计数量
            if (s.charAt(i) == '(') {
                left++;
            }
            // 当前为右括号、且前面有左括号
            else if (left > 0) {
                // 当前位置生成一个配对：连续有效括号最大长度 + 2
                d[i] = d[i - 1] + 2;

                // 前面相邻的有效配对：i - d[i] 表示当跳过当前最大匹配长度后的下标，取该位置的状态值
                // 比如 ( ) ( ( ) ( ) ) ( )，匹配最后一对“( )”的时候，还要把“( ( ) ( ) )”前面的“( )”算上
                d[i] += (i >= d[i]) ? d[i - d[i]]: 0;
                left--;
                max = Math.max(max, d[i]);
            }
        }

        return max;
    }
}
