package com.ywh.problem.leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 笨阶乘
 * [栈] [数学]
 *
 *
 * @author ywh
 * @since 4/2/2021
 */
public class LeetCode1006 {

    /**
     * 栈解法
     *
     * Time: O(n), Space: O(n)
     *
     * @param N
     * @return
     */
    public int clumsyStack(int N) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(N--);

        // 用于控制乘、除、加、减
        for (int i = 0; N > 0; i++, N--) {
            if (i % 4 == 0) {
                stack.push(stack.pop() * N);
            } else if (i % 4 == 1) {
                stack.push(stack.pop() / N);
            } else if (i % 4 == 2) {
                stack.push(N);
            } else {
                stack.push(-N);
            }
        }
        return stack.stream().reduce(Integer::sum).orElse(0);
    }

    /**
     * 数学解法
     *
     * Time: O(1), Space: O(1)
     *
     * @param N
     * @return
     */
    public int clumsyMath(int N) {
        if (N == 1) {
            return 1;
        } else if (N == 2) {
            return 2;
        } else if (N == 3) {
            return 6;
        } else if (N == 4) {
            return 7;
        }
        if (N % 4 == 0) {
            return N + 1;
        } else if (N % 4 <= 2) {
            return N + 2;
        } else {
            return N - 1;
        }
    }
}
