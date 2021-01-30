package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.Stack;

/**
 * 相同的树
 * [树] [栈]
 *
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 示例 1:
 *      输入:       1         1
 *                / \       / \
 *               2   3     2   3
 *
 *              [1,2,3],   [1,2,3]
 *      输出: true
 * 示例 2:
 *      输入:      1          1
 *                /           \
 *               2             2
 *
 *              [1,2],     [1,null,2]
 *      输出: false
 * 示例 3:
 *      输入:       1         1
 *                / \       / \
 *               2   1     1   2
 *
 *              [1,2,1],   [1,1,2]
 *      输出: false
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
