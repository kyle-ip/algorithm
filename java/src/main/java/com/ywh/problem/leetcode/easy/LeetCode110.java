package com.ywh.problem.leetcode.easy;

import com.ywh.model.TreeNode;

/**
 * 判断二叉树是否平衡
 * [树] [DFS]
 *
 * @author ywh
 * @since 2/18/2019
 */
public class LeetCode110 {

    /**
     * 求二叉树高度
     *
     * @param root
     * @return
     */
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right));
    }

    /**
     * Time: O(nlog(n)), Space: O(n)
     *
     * @param root
     * @return
     */
    public boolean isBalancedTreeTopDown(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode left = root.left, right = root.right;
        return Math.abs(getHeight(left) - getHeight(right)) < 2
            && isBalancedTreeTopDown(left)
            && isBalancedTreeTopDown(right);
    }

    /**
     * TODO 了解为何返回 -1
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public boolean isBalancedTreeBottomUp(TreeNode root) {
        return getHeightAndCheck(root) != -1;
    }

    int getHeightAndCheck(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeightAndCheck(root.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = getHeightAndCheck(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

}
