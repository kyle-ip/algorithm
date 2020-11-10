package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

/**
 * 二叉搜索树中查找数字
 * [树]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode700 {

    /**
     * Time: O(h), Space: O(h)
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBSTRecursive(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        } else if (root.val < val) {
            return searchBSTRecursive(root.right, val);
        } else {
            return searchBSTRecursive(root.left, val);
        }
    }

    /**
     * Time: O(h), Space: O(1)
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBSTIterative(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = root.val < val? root.right: root.left;
        }
        return root;
    }

}
