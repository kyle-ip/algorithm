package com.ywh.problem.leetcode.easy;

import java.util.Stack;

/**
 * 带有 min 函数的栈带有 min 函数的栈
 * [栈] [设计]
 *
 * @author ywh
 * @since 2/21/2019
 */
public class LeetCode155 {

    /**
     * 使用两个栈实现
     */
    public class MinStackWithTwoStack {

        private Stack<Integer> stack = new Stack<>();
        private Stack<Integer> min = new Stack<>();

        public int getMin() {
            return min.peek();
        }

        public void pop() {
            if (stack.isEmpty()) {
                return;
            }
            if (!min.isEmpty() && stack.peek() == getMin()) {
                min.pop();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public void push(int x) {
            stack.push(x);
            if (x <= min.peek()) {
                min.push(x);
            }
        }

    }

    /**
     * 使用一个栈实现：每次入栈、需要更新最小值时则把上次的最小值和目标元素一并入栈，可记录每个阶段的最小值
     */
    public class MinStackWithLinkedList {

        private Stack<Integer> stack = new Stack<>();
        private int min = Integer.MAX_VALUE;

        public int getMin() {
            return min;
        }

        /**
         * 出栈时，如果栈顶元素等于当前最小值，则把最小值设为栈顶元素的下一个元素（即“次小值”）、跳过两个元素即可（栈顶元素、上次最小值）；
         * 否则，栈顶元素出栈即可
         */
        public void pop() {
            if (stack.isEmpty()) {
                return;
            }
            if (stack.peek() == min) {
                stack.pop();
                min = stack.peek();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        /**
         * 入栈时，如果目标元素比当前最小值小，则先把当前最小值入栈、再把目标元素入栈（两个节点入栈）；
         * 否则，只把目标元素入栈即可（一个节点入栈）
         *
         * @param x
         */
        public void push(int x) {
            if (x <= min) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }
    }

}
