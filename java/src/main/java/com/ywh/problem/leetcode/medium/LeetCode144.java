package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树前序遍历
 * [栈] [树]
 *
 * @author ywh
 * @since 2/21/2019
 */
public class LeetCode144 {

    /**
     *
     * @param root
     * @param result
     */
    private void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalRecursive2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> left = preorderTraversalRecursive2(root.left), right = preorderTraversalIterative(root.right);
        left.add(0, root.val);
        left.addAll(right);
        return left;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            // 一直往左走，沿途添加节点的值到结果集，并把节点入栈，直到遇到空节点。
            if (root != null) {
                ret.add(root.val);
                stack.push(root);
                root = root.left;
            }
            // 当根、左都已遍历完时弹出时根节点，指向右节点。
            else {
                root = stack.pop().right;
            }
        }
        return ret;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalIterative2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);

            // 后进先出，先把右节点入栈，确保下次先处理左节点
            if (node.right!= null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }
}
