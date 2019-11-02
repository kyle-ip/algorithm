package com.ywh.problem.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 翻转二叉树
 * [树]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode226 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public TreeNode invertBinaryTreeRecursive(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        tmp.left = root.right;
        root.right = tmp;

        invertBinaryTreeRecursive(root.left);
        invertBinaryTreeRecursive(root.right);

        return root;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public  TreeNode invertBinaryTreeIterative(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return root;
    }
}
