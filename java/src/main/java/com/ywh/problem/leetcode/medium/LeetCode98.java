package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;

/**
 * 检验二叉搜索树
 * [树] [DFS]
 *
 * @author ywh
 * @since 27/10/2019
 */
public class LeetCode98 {

    private TreeNode max(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    private TreeNode min(TreeNode root) {
        while (root.left != null) {
            root = root.left;
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
        return leftValid && rightValid && isValidBSTBound(root.left) && isValidBSTBound(root.right);
    }

    /**
     * 在递归参数中传递边界值
     *
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

        // 当前节点存在下/上界，且下/上界的值大于/小于等于当前节点值，返回错误。
        if ((lower != null && lower.val >= root.val) || (upper != null && upper.val <= root.val)) {
            return false;
        }

        // 对于左/右子树，下/上界为 root 的下/上界，上/下界为 root
        return isValidBSTBound(root.left, lower, root) && isValidBSTBound(root.right, root, upper);
    }
}
