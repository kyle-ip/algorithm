package com.ywh.problem.leetcode.hard;

import java.util.Stack;

/**
 * 直方图中的最大矩形
 * [数组] [栈]
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *            |
 *          | |
 *          | |
 *          | |   |
 *      |   | | | |
 *      | | | | | |
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * 示例：
 *      输入: [2,1,5,6,2,3]
 *      输出: 10
 *
 * @author ywh
 * @since 16/11/2019
 */
public class LeetCode84 {

    /**
     * 最大矩形的面积取决于高度较低的一边。
     * 选取高度值，从该点向两边扩展。
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
        // 从右向左遍历直方图。
        for (int i = 0; i < n; i++) {
            // 双指针从 i 出发向两边移动，直到两边的高度小于中间。
            int l = i, r = i;
            for (; l >= 0 && heights[l] >= heights[i]; l--);
            for (; r < n && heights[r] >= heights[i]; r++);
            // 此时 l 和 r 之间的范围内中间低、两边高，阴影面积的高度为 height[i]。
            // 由于 l 和 r 都指向不符合条件的位置，宽度应为 (l, r) 开区间的长度。
            max = Math.max(max, heights[i] * (r - l - 1));
        }
        return max;
    }

    /**
     * 单调栈：使用辅助栈维护一个下标序列，这些下标对应的直方图高度依次递增。
     * 当 r 对应的高度小于栈顶下标对应的高度时，对于栈顶下标对应的高度而言：
     * 下标 r 是它的右边界，栈顶的前一个元素就是它的左边界。
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
        int ret = 0, n = heights.length;

        // 使用辅助栈缓存高度数组的下标。
        Stack<Integer> stack = new Stack<>();

        // 遍历到数组最后一个元素的后一位（目的是处理最后一个柱子）。
        for (int r = 0; r <= n; r++) {

            // 获取当前高度，越界则定义为 0。
            int h = r == n? 0: heights[r];

            // 栈不为空，且越过极大值点（相对于栈顶而言，开始下降）。
            // 一直弹出，直到 l 高度小于 r 高度，则 (l, r) 表示矩形的宽，栈顶所指的高度为矩形的高。

            //     [ ] [ ]                  [ ]              [ ][ ]
            //     [ ] [ ] [ ]              [ ]              [ ][ ]
            // [ ] [ ] [ ] [ ]      =>      [ ]      =>      [ ][ ]
            // [0] [1] [2] [3]              [ ]              [ ][ ]
            //  l  idx      r         s == (r-l-1) * height[idx] == (3-1-0) * h[1] == 4
            while (!stack.empty() && h < heights[stack.peek()]) {

                // 则弹出栈顶的极大值点，下一个元素为左边界下标（如果弹出极大值后没有元素，则置为 -1）。
                int idx = stack.pop();
                int l = stack.empty()?  -1: stack.peek();

                // 由于 r 表示开始递减的位置的下标，所以底边应为 (l, r) 区间的长度，所以是 r-l-1。
                ret = Math.max(ret, heights[idx] * (r - l - 1));
            }

            // 栈为空或高度递增/持平，把下标入栈。
            stack.push(r);
        }

        return ret;
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
        for (int r = 0; r <= n; r++) {
            int h = r == n? 0: heights[r];
            while (top != -1 && h < heights[stack[top]]) {
                int idx = stack[top--];
                int l = top != -1? stack[top]: -1;
                max = Math.max(max, heights[idx] * (r - l - 1));
            }
            stack[++top] = r;
        }
        return max;
    }
}
