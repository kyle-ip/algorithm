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

    public void inorderTraversal(TreeNode root, List<Integer> ret) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, ret);
        ret.add(root.val);
        inorderTraversal(root.right, ret);
    }

    public List<Integer> inorderTraversalRecursive2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        inorderTraversal(root, ret);
        return ret;
    }

    /**
     * 中序遍历即左根右
     *
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 取左子树的节点列表。
        List<Integer> left = inorderTraversalRecursive(root.left);
        // 取右子树的节点列表
        List<Integer> right = inorderTraversalRecursive(root.right);
        // 在左子树的节点后面添加根节点、再依次添加所有右子树的节点。
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
        List<Integer> ret = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                ret.add(root.val);
                root = root.right;
            }
        }
        return ret;
    }
}
