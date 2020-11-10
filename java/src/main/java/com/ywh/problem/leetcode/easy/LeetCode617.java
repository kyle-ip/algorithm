package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

/**
 * 合并二叉树
 * [树]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode617 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        if (t1 == null || t2 == null) {
            return t1 == null? t2: t1;
        }
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

}
