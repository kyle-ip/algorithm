package com.ywh.problem.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最大深度
 * [树] [DFS]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode104 {

    /**
     * 递归解法
     * Time: O(n), Space: (n)
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 层次遍历解法
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public int maxDepthIterative(TreeNode root) {
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
