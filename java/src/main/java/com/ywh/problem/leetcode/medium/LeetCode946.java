
package com.ywh.problem.leetcode.medium;

import java.util.Stack;

/**
 * 验证入栈出栈序列
 * [栈]
 *
 * @author ywh
 * @since 26/11/2019
 */
public class LeetCode946 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) {
            return false;
        }

        // 使用辅助栈模拟入栈出栈过程
        Stack<Integer> stack = new Stack<>();

        // 用于记录出栈数组当前位置（验证时需要逐个比对）
        int idx = 0;

        // 循环入栈数组，把元素推入辅助栈
        for (int num : pushed) {
            stack.push(num);

            // 如果辅助栈非空，且栈顶元素等于出栈数组中的当前元素，表示比对成功，这是正确的出栈顺序
            // 从辅助栈中弹出、下次与出栈数组的下一个元素比对
            while (!stack.isEmpty() && stack.peek() == popped[idx]) {
                stack.pop();
                idx++;
            }
        }

        // 如果直到最后辅助栈仍有元素，表示出栈数组与辅助栈栈顶元素比较时始终对不上，因此不可能是正确的出栈序列
        return stack.isEmpty();
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequencesArray(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) {
            return false;
        }
        int[] stack = new int[pushed.length];
        int idx = 0, p = -1;
        for (int num : pushed) {
            stack[++p] = num;
            while (p != -1 && popped[idx] == stack[p]) {
                p--;
                idx++;
            }
        }
        return p == -1;
    }
}
