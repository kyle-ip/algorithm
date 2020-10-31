package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树后序遍历
 * [树] [栈]
 *
 * @author ywh
 * @since 06/11/2019
 */
public class LeetCode145 {

    /**
     *
     * @param root
     * @param result
     */
    private void postorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return  result;
    }

    public List<Integer> postorderTraversalRecursive2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> left = postorderTraversalRecursive2(root.left), right = postorderTraversalRecursive2(root.right);
        left.addAll(right);
        left.add(root.val);
        return left;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.empty()) {

            // 一直遍历把节点入栈，直到到达最左节点
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.peek().right;

                // 如果栈顶节点不存在右节点，或栈顶节点的右节点上次已遍历过
                //   [1]            [1]
                //     \      或
                //      (2)
                // 标记 1 为上次遍历的节点，把 1 加入结果，并把 root 置空
                if (root == null || root == pre) {
                    pre = stack.pop();
                    result.add(pre.val);
                    root = null;
                }
            }
        }
        return result;
    }

    /**
     * 利用栈对树做先右后左的深度优先遍历，每次遍历元素插入到链表头部（时间 O(1)）
     * 最终返回的链表即为后序遍历
     *
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversalIterative2(TreeNode root) {
        LinkedList<Integer> ret = new LinkedList<>();
        if (root == null) {
            return ret;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            // 使用链表插入元素到头部
            ret.addFirst(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return ret;
    }

}
