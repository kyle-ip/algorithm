package com.ywh.problem.leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 验证前序遍历序列二叉搜索树
 * [树] [栈] [分治]
 *
 * 给定一个整数数组，你需要验证它是否是一个二叉搜索树正确的先序遍历序列。
 * 你可以假定该序列中的数都是不相同的。
 * 参考以下这颗二叉搜索树：
 *           5
 *          / \
 *         2   6
 *        / \
 *       1   3
 * 示例 1：
 *      输入: [5,2,6,1,3]
 *      输出: false
 * 示例 2：
 *      输入: [5,2,1,3,6]
 *      输出: true
 * 进阶挑战：
 *      您能否使用恒定的空间复杂度来完成此题？
 *
 * @author ywh
 * @since 07/01/2020
 */
public class LeetCode255 {

    /**
     * 每次处理 [start, end) 范围内的值，验证是否前序遍历
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private boolean verify(int[] nums, int start, int end) {
        // 处理到最后返回 true。
        if (start == end || start + 1 == end) {
            return true;
        }

        // 取树根：[4], 1, 0, 2, 8
        int root = nums[start];

        // 验证左子树：4, (1, 0, 2), 8
        int i = start + 1;
        for (; i < end && nums[i] < root; i++);

        // 验证右子树：4, 1, 0, 2, (8)
        int mid = i;
        for (; i < end && nums[i] > root; i++);

        // 如果验证到尽头，满足根节点大于左子树、小于右子树的条件，则递归验证其左右子树，否则返回 false。
        if (i != end) {
            return false;

        }
        return verify(nums, start + 1, mid) && verify(nums, mid, end);
    }

    /**
     * 分治解法（自顶向下）
     *
     * Time: O(n^2), Space: O(n)
     *
     * @param preorder
     * @return
     */
    public boolean verifyPreorderDivideConquer(int[] preorder) {
        if (preorder == null) {
            return false;
        }
        return verify(preorder, 0, preorder.length);
    }

    /**
     * 栈解法（自底向上，每轮对比结果可以复用下一轮）
     *
     * Time: O(n), Space: O(n)
     *
     * @param preorder
     * @return
     */
    public boolean verifyPreorderStack(int[] preorder) {
        if (preorder == null) {
            return false;
        }

        Deque<Integer> stack = new LinkedList<>();
        int lowerBound = Integer.MIN_VALUE;

        // 逐个元素判断
        for (int num: preorder) {

            // 如果元素小于下界，表示右子树的元素小于父节点或左子树
            if (num < lowerBound) {
                return false;
            }
            // 如果栈非空，且当前元素大于栈顶元素，则求当前元素在栈中的下界（下界表示栈中小于当前元素的最大值）
            while (!stack.isEmpty() && num > stack.peek()) {
                lowerBound = stack.pop();
            }

            // 逐个元素入栈（当出现当前元素比栈顶元素大时会不断出栈、求下界，因此相当于先把左分支入栈、再处理右分支）
            stack.push(num);
        }
        return true;
    }

    /**
     * 运用数组实现栈
     *
     * Time: O(n), Space: O(n)
     *
     * @param preorder
     * @return
     */
    public boolean verifyPreorderArray(int[] preorder) {
        if (preorder == null) {
            return false;
        }
        int[] stack = new int[preorder.length];
        int lowerBound = Integer.MIN_VALUE, top = -1;
        for (int num : preorder) {
            if (num < lowerBound) {
                return false;
            }
            while (top != -1 && num > stack[top]) {
                lowerBound = stack[top--];
            }
            stack[++top] = num;
        }
        return true;
    }

    /**
     * 复用入参数组
     *
     * Time: O(n), Space: O(1)
     *
     * @param preorder
     * @return
     */
    public boolean verifyPreorderArrayO1Space(int[] preorder) {
        if (preorder == null) {
            return false;
        }
        //     5
        //    / \
        //   2   6
        //    \   \
        //     4   7    5, 2, 4, 6, 7
        int lowerBound = Integer.MIN_VALUE, top = -1;
        for (int num : preorder) {
            if (num < lowerBound) {
                return false;
            }
            while (top != -1 && num > preorder[top]) {
                lowerBound = preorder[top--];
            }
            preorder[++top] = num;
        }
        return true;
    }
}
