package com.ywh.problem.leetcode.hard;

import java.util.Stack;

/**
 * 直方图中的最大矩形
 * [数组] [栈]
 *
 * @author ywh
 * @since 16/11/2019
 */
public class LeetCode84 {

    /**
     * 最大矩形的面积取决于高度较低的一边
     * 选取高度值，从该点向两边扩展
     *
     * Time: O(n^2), Space: O(1)
     *
     * @param heights
     * @return
     */
    public int largestRectangleAreaExpand(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = 0, n = heights.length;
        for (int i = 0; i < n; i++) {
            int left = i, right = i;
            while (left >= 0 && heights[left] >= heights[i]) {
                --left;
            }
            while (right < n && heights[right] >= heights[i]) {
                ++right;
            }

            // 由于 left 和 right 都指向不符合条件的位置，宽度应为 (left, right) 开区间的长度
            // 两边都比 height[i]，因此取 height[i] 为高
            max = Math.max(max, heights[i] * (right - left - 1));
        }
        return max;
    }

    /**
     * 单调栈：使用辅助栈维护一个下标序列，这些下标对应的直方图高度依次递增。
     * 当 r 对应的高度小于栈顶下标对应的高度时，对于栈顶下标对应的高度来说：
     * 下标 r 是它的右边界，栈顶的前一个元素就是它的左边界
     *
     * Time: O(n), Space: O(n)
     *
     * @param heights
     * @return
     */
    public int largestRectangleAreaStack(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = 0, n = heights.length;

        // 使用辅助栈缓存高度数组的下标
        Stack<Integer> stack = new Stack<>();

        // 遍历到数组最后一个元素的后一位（目的是把辅助栈剩下的元素处理完）
        for (int right = 0; right <= n; right++) {

            // 获取当前高度（越界则定义为 0）
            int h = right == n? 0: heights[right];

            // 栈不为空，且高度开始递减（当前高度大于栈顶元素高度）
            while (!stack.isEmpty() && h < heights[stack.peek()]) {

                // 则栈顶为当前最大高度（弹出），下一个元素为左边界下标
                int idx = stack.pop();

                // 如果弹出后没有元素（即一开始）
                int left = stack.empty()?  -1: stack.peek();

                // 由于 right 表示开始递减的位置的下标，所以底边应为 right - 1 与 left 之间的距离
                max = Math.max(max, heights[idx] * (right - left - 1));
            }

            // 栈为空，且高度依然递增，把高度入栈
            stack.push(right);
        }

        return max;
    }

    /**
     * 使用数组实现的辅助栈（注意需要辅助指针）
     *
     * Time: O(n), Space: O(n)
     *
     * @param heights
     * @return
     */
    public int largestRectangleAreaArray(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int max = 0, n = heights.length, top = -1;
        int[] stack = new int[n + 1];
        for (int right = 0; right <= n; right++) {
            int h = right == n? 0: heights[right];
            while (top != -1 && h < heights[stack[top]]) {
                int idx = stack[top--];
                int left = top != -1? stack[top]: -1;
                max = Math.max(max, heights[idx] * (right - left - 1));
            }
            stack[++top] = right;
        }
        return max;
    }
}
