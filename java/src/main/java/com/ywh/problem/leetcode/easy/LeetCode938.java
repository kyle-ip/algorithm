package com.ywh.problem.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.Stack;

/**
 * 二叉搜索树的区间和
 * [树]
 *
 * @author ywh
 * @since 18/04/2020
 */
public class LeetCode938 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBSTRecursive(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        // 当前节点的值小/大于左/右边界，不在求和范围中，因此向右/左递归
        if (root.val < L) {
            return rangeSumBSTRecursive(root.right, L, R);
        }
        if (root.val > R) {
            return rangeSumBSTRecursive(root.left, L, R);
        }
        return root.val + rangeSumBSTRecursive(root.left, L, R) + rangeSumBSTRecursive(root.right, L, R);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBSTIterative(TreeNode root, int L, int R) {
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            if (node.val < L) {
                stack.push(node.right);
            } else if (node.val > R) {
                stack.push(node.left);
            } else {
                sum += node.val;
                stack.push(node.left);
                stack.push(node.right);
            }
        }
        return sum;
    }
}
