package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最大深度
 * [树] [深度优先搜索]
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明:
 *      叶子节点是指没有子节点的节点。
 * 示例：
 *      给定二叉树 [3,9,20,null,null,15,7]，
 *          3
 *         / \
 *        9  20
 *          /  \
 *         15   7
 *      返回它的最大深度 3 。
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
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
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
