package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.Stack;

/**
 * 判断二叉树是否相同
 * [树] [栈]
 *
 * @author ywh
 * @since 2/14/2019
 */
public class LeetCode100 {

    /**
     * 递归解法
     * Time: O(n), Space: O(n)
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTreeIterative(p.left, q.left) && isSameTreeIterative(p.right, q.right);

    }

    /**
     * 迭代解法
     * Time: O(n), Space: O(n)
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(p);
        stack.push(q);
        while (!stack.isEmpty()) {
            TreeNode r = stack.pop(), l = stack.pop();
            if (l == null && r == null) {
                continue;
            }
            if (l == null || r == null || l.val != r.val) {
                return false;
            }
            stack.push(l.left);
            stack.push(r.left);
            stack.push(l.right);
            stack.push(r.right);
        }
        return true;
    }
}
