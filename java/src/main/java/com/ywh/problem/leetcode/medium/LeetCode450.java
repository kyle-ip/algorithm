package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

/**
 * 二叉搜索树中删除节点
 * [树]
 * 
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * 示例:
 *      root = [5,3,6,2,4,null,7]
 *      key = 3
 *          5
 *         / \
 *        3   6
 *       / \   \
 *      2   4   7
 *      给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 *      一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 *          5
 *         / \
 *        4   6
 *       /     \
 *      2       7=
 *      另一个正确答案是 [5,2,6,null,4,null,7]。
 *          5
 *         / \
 *        2   6
 *         \   \
 *          4   7
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
     * @param key
     * @return
     */
    public TreeNode deleteNodeInBST(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNodeInBST(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNodeInBST(root.right, key);
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
