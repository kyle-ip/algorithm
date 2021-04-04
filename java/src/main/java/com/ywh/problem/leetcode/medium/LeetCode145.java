package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.*;

/**
 * 二叉树的后序遍历
 * [树] [栈]
 * 
 * 给定一个二叉树，返回它的 后序 遍历。
 * 示例:
 *      输入: [1,null,2,3]
 *         1
 *          \
 *           2
 *          /
 *         3
 *      输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author ywh
 * @since 06/11/2019
 */
public class LeetCode145 {

    /**
     *
     * @param root
     * @param ret
     */
    private void postorder(TreeNode root, List<Integer> ret) {
        if (root == null) {
            return;
        }
        postorder(root.left, ret);
        postorder(root.right, ret);
        ret.add(root.val);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        postorder(root, ret);
        return  ret;
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
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {

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
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
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
