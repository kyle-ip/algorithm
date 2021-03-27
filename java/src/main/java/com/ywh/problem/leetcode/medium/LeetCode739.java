package com.ywh.problem.leetcode.medium;

import java.util.Stack;

/**
 * 每日温度
 * [栈] [数组]
 *
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * @author ywh
 * @since 08/07/2020
 */
public class LeetCode739 {

    /**
     * Time: O(n^2), Space: O(n)
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
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 辅助栈栈顶下标所指的元素小于当前元素，表示距离 st.peek() 最近且比 T[st.peek()] 大的元素为 T[i]，距离 i - st.peek()
            // 可能不止一个，所以一直循环处理，比如：3, 2, 6，则 ret[0] == 2, ret[1] == 1，都是 6
            // 每轮都要把栈中的元素全部摊出来，直到栈空或者栈不递减。
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
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
        int[] ret = new int[T.length];

        // 从后向前遍历。
        for (int i = T.length - 2; i >= 0; i--) {
            int j = i + 1;

            // 如果在 j 这天气温下降（且累计天数数组值不为 0），表示应跳过 j + ret[j] 天。
            while (j < T.length && T[j] <= T[i] && ret[j] != 0) {
                j += ret[j];
            }

            // 如果跳完后 j 的气温高于 i，则更新 ret[i]。
            if (T[i] < T[j]) {
                ret[i] = j - i;
            }
        }
        return ret;
    }
}
