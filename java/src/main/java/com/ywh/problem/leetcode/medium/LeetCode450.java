package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

/**
 * 二叉搜索树中删除节点
 * [树]
 *
 * @author ywh
 * @since 03/11/2019
 */
public class LeetCode450 {

    /**
     * 对于树的问题，优先考虑递归；
     * 对于二叉搜索树，则判断大于小于等于三种情况；
     * 根据返回值为树节点，所以递归项的返回结果作为当前节点的左/右节点
     *
     * Time: O(h), Space: O(h)
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode deleteNodeInBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val > val) {
            root.left = deleteNodeInBST(root.left, val);
        }
        else if (root.val < val) {
            root.right = deleteNodeInBST(root.right, val);
        }
        // 找到待删除节点
        else {
            // 如其中一边节点为空，返回另一边即可
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 如两边都不为空，则合并左右子树再返回
            // 先找到左子树中的最大值（最右边节点），并把右子树作为这个最大节点的右子树
            TreeNode leftMax = root.left;
            while (leftMax.right != null) {
                leftMax = leftMax.right;
            }
            leftMax.right = root.right;

            // 最后返回被删除节点的左子树
            root = root.left;
        }
        return root;
    }

}
