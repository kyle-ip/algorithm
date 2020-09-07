package com.ywh.problem.leetcode.easy;


import com.ywh.model.TreeNode;

/**
 * 二叉搜索树中节点的最近公共祖先
 * [树]
 *
 * @author ywh
 * @since 2/14/2019
 */
public class LeetCode235 {

    /**
     * 当输入的两个节点分布在根节点的两边，则最近公共祖先为根节点；
     * 否则根据它们与根节点的相对大小，在根节点的左/右子树搜索
     *
     * Time: O(h), Space: O(h)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lcaRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lcaRecursive(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lcaRecursive(root.right, p, q);
        } else {
            return root;
        }
    }

    /**
     * Time: O(h), Space: O(1)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lcaIterative(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
}