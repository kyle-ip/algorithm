package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最小深度
 * [树] [深度优先搜索] [广度优先搜索]
 *
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 * 示例 1：
 *      输入：root = [3,9,20,null,null,15,7]
 *      输出：2
 * 示例 2：
 *      输入：root = [2,null,3,null,4,null,5,null,6]
 *      输出：5
 * 提示：
 *      树中节点数的范围在 [0, 10^5] 内
 *      -1000 <= Node.val <= 1000
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
    public int minDepthRecursive(TreeNode root) {
        // 空树为 0。
        if (root == null) {
            return 0;
        }

        // 无子树最小深度为 1。
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 只有左/右子树，则对左/右子树求深度。
        if (root.left == null || root.right == null) {
            return root.left == null? minDepthRecursive(root.right) + 1: minDepthRecursive(root.left) + 1;
        }

        // 左右子树都存在，取较小者。
        return Math.min(minDepthRecursive(root.left), minDepthRecursive(root.right)) + 1;
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
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
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
        }
        return depth;
    }
}
