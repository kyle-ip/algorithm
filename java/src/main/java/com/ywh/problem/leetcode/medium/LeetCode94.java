package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树中序遍历
 * [栈] [树]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode94 {

    /**
     * Time: O(n). Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> left = inorderTraversalRecursive(root.left);
        List<Integer> right = inorderTraversalRecursive(root.right);
        left.add(root.val);
        left.addAll(right);
        return left;
    }

    /**
     * Time: O(n). Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root.left);
                root = root.left;
            } else {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }
}
