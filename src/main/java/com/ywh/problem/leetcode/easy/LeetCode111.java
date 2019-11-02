package com.ywh.problem.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最小深度
 * [树] [DFS] [BFS]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode111 {

    /**
     * 递归解法
     * Time: O(n), Space: (n)
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        // 空树为 0
        if (root == null) {
            return 0;
        }

        // 无子树最小深度为 1
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 只有左/右子树，则对右/左子树求深度
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        // 左右子树都存在，取较小者
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * 层次遍历解法
     * Time: O(n), Space: (n)
     *
     * @param root
     * @return
     */
    public int minDepthIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1, size;

        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            depth++;
        }
        return depth;
    }
}
