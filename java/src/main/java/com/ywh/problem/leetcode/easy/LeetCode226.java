package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 翻转二叉树
 * [树]
 *
 * 翻转一棵二叉树。
 * 示例：
 *      输入：
 *                4
 *              /   \
 *             2     7
 *            / \   / \
 *           1   3 6   9
 *      输出：
 *                4
 *              /   \
 *             7     2
 *            / \   / \
 *           9   6 3   1
 * 备注:
 *      这个问题是受到 Max Howell 的 原问题 启发的 ：
 *      谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
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
        root.left = root.right;
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
    public TreeNode invertBinaryTreeIterative(TreeNode root) {
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
