package com.ywh.problem.leetcode.easy;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 最小栈
 * [栈] [设计]
 * 
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *      push(x) —— 将元素 x 推入栈中。
 *      pop() —— 删除栈顶的元素。
 *      top() —— 获取栈顶元素。
 *      getMin() —— 检索栈中的最小元素。
 * 示例:
 * 输入：
 *      ["MinStack","push","push","push","getMin","pop","top","getMin"]
 *      [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 *      [null,null,null,null,-3,null,0,-2]
 * 解释：
 *      MinStack minStack = new MinStack();
 *      minStack.push(-2);
 *      minStack.push(0);
 *      minStack.push(-3);
 *      minStack.getMin();   --> 返回 -3.
 *      minStack.pop();
 *      minStack.top();      --> 返回 0.
 *      minStack.getMin();   --> 返回 -2.
 * 提示：
 *      pop、top 和 getMin 操作总是在 非空栈 上调用。
 *
 * @author ywh
 * @since 2/21/2019
 */
public class LeetCode155 {

    /**
     * 使用两个栈实现
     */
    public static class MinStackWithTwoStack {

        private final Deque<Integer> stack = new LinkedList<>(), min = new LinkedList<>();

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

        private Deque<Integer> stack = new LinkedList<>();

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
         * 否则，只把目标元素入栈即可（一个节点入栈）。
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
