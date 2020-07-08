package com.ywh.problem.leetcode.medium;

import java.util.Stack;

/**
 * 温度升高需要等待的天数
 * [栈] [数组]
 *
 * @author ywh
 * @since 08/07/2020
 */
public class LeetCode739 {
    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param T
     * @return
     */
    public int[] dailyTemperaturesBruteForce(int[] T) {
        int n  = T.length;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && T[j] < T[i]) {
                j += 1;
            }
            if (j < n) {
                ret[i] = j - i;
            }
            // else result[i] = 0;
        }
        return ret;
    }

    /**
     * 栈解法
     *
     * Time: O(n), Space: O(n)
     *
     * @param T
     * @return
     */
    public int[] dailyTemperaturesStack(int[] T) {
        int n = T.length;
        int[] ret = new int[n];

        // 辅助栈存储数组下标，确保辅助栈中的下标所指的元素都是递减的
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 辅助栈栈顶元素所指的原数组元素小于当前元素，表示距离 st.peek() 最近且比 T[st.peek()] 大的元素为 T[i]，距离 i - st.peek()
            // 可能不止一个，所以一直循环处理，比如：3, 2, 6，则 ret[0] == 2, ret[1] == 1，都是 6
            while (!st.isEmpty() && T[st.peek()] < T[i]) {
                int idx = st.pop();
                ret[idx] = i - idx;
            }
            st.push(i);
        }
        // while (!st.empty()) ret[st.pop()] = 0;
        return ret;
    }

    /**
     * 跳跃解法：从后往前，利用已解出的等待天数跳跃求解
     *
     * Time: O(n), Space: O(1)
     *
     * @param T
     * @return
     */
    public int[] dailyTemperaturesSkip(int[] T) {

        int n = T.length;
        int[] ret = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while (T[j] <= T[i] && ret[j] != 0) {
                j += ret[j];
            }
            if (T[j] > T[i]) {
                ret[i] = j - i;
            }
            // else result[i] = 0;
        }
        return ret;
    }
}
