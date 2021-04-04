package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

/**
 * 检验二叉搜索树
 * [树] [深度优先搜索]
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 *      节点的左子树只包含小于当前节点的数。
 *      节点的右子树只包含大于当前节点的数。
 *      所有左子树和右子树自身必须也是二叉搜索树。
 * 示例  1:
 *      输入:
 *          2
 *         / \
 *        1   3
 *      输出: true
 * 示例  2:
 *      输入:
 *          5
 *         / \
 *        1   4
 *            / \
 *           3   6
 *      输出: false
 *      解释: 输入为: [5,1,4,null,null,3,6]。
 *            根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * @author ywh
 * @since 27/10/2019
 */
public class LeetCode98 {

    /**
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
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftValid = root.left == null || root.val > max(root.left).val,
            rightValid = root.right == null || root.val < min(root.right).val;
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

        // 对于左/右子树，下/上界为 root 的下/上界，上/下界为 root。
        return isValidBSTBound(root.left, lower, root) && isValidBSTBound(root.right, root, upper);
    }
}
