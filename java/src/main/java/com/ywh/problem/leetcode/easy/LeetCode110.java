package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

/**
 * 平衡二叉树
 * [树] [深度优先搜索]
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * 示例 1：
 *      输入：root = [3,9,20,null,null,15,7]
 *      输出：true
 * 示例 2：
 *      输入：root = [1,2,2,3,3,null,null,4,4]
 *      输出：false
 * 示例 3：
 *      输入：root = []
 *      输出：true
 * 提示：
 *      树中的节点数在范围 [0, 5000] 内
 *      -10^4 <= Node.val <= 10^4
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
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
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
        TreeNode l = root.left, r = root.right;
        return Math.abs(getHeight(l) - getHeight(r)) < 2
            && isBalancedTreeTopDown(l)
            && isBalancedTreeTopDown(r);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public boolean isBalancedTreeBottomUp(TreeNode root) {
        return getHeightAndCheck(root) != -1;
    }

    /**
     * 求树的高度，如果不平衡，则在求高度过程中直接返回 -1。
     *
     * @param root
     * @return
     */
    private int getHeightAndCheck(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = getHeightAndCheck(root.left);
        if (l == -1) {
            return -1;
        }
        int r = getHeightAndCheck(root.right);
        if (r == -1) {
            return -1;
        }
        return Math.abs(l - r) > 1? -1: Math.max(l, r) + 1;
    }

}
