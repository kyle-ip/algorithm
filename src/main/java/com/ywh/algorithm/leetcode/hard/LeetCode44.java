package com.ywh.algorithm.leetcode.hard;

import com.ywh.model.TreeNode;

/**
 * 检验二叉搜索树
 * [树] [DFS]
 *
 * @author ywh
 * @since 27/10/2019
 */
public class LeetCode44 {

    /**
     * 二叉搜索树取最小节点
     *
     * @param root
     * @return
     */
    private TreeNode min(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * 二叉搜索树取最大节点
     *
     * @param root
     * @return
     */
    private TreeNode max(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftValid = root.left == null || root.val > max(root.left).val;
        boolean rightValid = root.right == null || root.val < min(root.right).val;
        return leftValid && rightValid && isValidBST(root.left) && isValidBST(root.right);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public boolean isValidBSTBound(TreeNode root) {
        return isValidBSTBound(root, null, null);
    }

    /**
     * @param root
     * @param lower
     * @param upper
     * @return
     */
    boolean isValidBSTBound(TreeNode root, TreeNode lower, TreeNode upper) {
        if (root == null) {
            return true;
        }
        if (lower != null && lower.val >= root.val) {
            return false;
        }
        if (upper != null && upper.val <= root.val) {
            return false;
        }
        return isValidBSTBound(root.left, lower, root) && isValidBSTBound(root.right, root, upper);
    }


}
