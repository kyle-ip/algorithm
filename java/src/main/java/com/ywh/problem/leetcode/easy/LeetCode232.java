package com.ywh.problem.leetcode.easy;

import java.util.Stack;

/**
 * 使用栈实现队列
 * [栈] [设计]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode232 {

    private Stack<Integer> in = new Stack<>(), out = new Stack<>();

    private void transferFromInToOut() {
        if (out.empty()) {
            while (!in.empty()) {
                out.push(in.pop());
            }
        }
    }

    public void push(int x) {
        in.push(x);
    }

    public void pop() {
        transferFromInToOut();
        out.pop();
    }

    public int peek() {
        transferFromInToOut();
        return out.peek();
    }

    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}
