package com.ywh.problem.leetcode.hard;

import com.ywh.ds.tree.TreeNode;

/**
 * 二叉树中的最大路径和
 * [树] [深度优先搜索]
 *
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 该路径至少包含一个节点，且不一定经过根节点。
 * 示例 1：
 *      输入：[1,2,3]
 *             1
 *            / \
 *           2   3
 *      输出：6
 * 示例 2：
 *      输入：[-10,9,20,null,null,15,7]
 *         -10
 *         / \
 *        9  20
 *          /  \
 *         15   7
 *      输出：42
 *
 * @author ywh
 * @since 2020/9/16/016
 */
public class LeetCode124 {

    /**
     * @param a
     * @param b
     * @param c
     * @return
     */
    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    /**
     * @param root
     * @return
     */
    private int maxPathSumFrom(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + Math.max(Math.max(maxPathSumFrom(root.left), 0), Math.max(maxPathSumFrom(root.right), 0));
    }

    /**
     * Time: O(n^2), Space: O(n)
     *
     * @param root
     * @return
     */
    public int maxPathSumBruteForce(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        return max(
            root.val + Math.max(maxPathSumFrom(root.left), 0) + Math.max(maxPathSumFrom(root.right), 0),
            maxPathSumBruteForce(root.left),
            maxPathSumBruteForce(root.right)
        );
    }

    /**
     * 以 root 为根的最大路径和（不含 root）
     *
     * @param root
     * @param ret
     * @return
     */
    private int maxPathSumOn(TreeNode root, int[] ret) {
        //     -4
        //    /  \
        //   1    2
        //  / \
        // 4   8

        // 叶子节点，返回 0。
        if (root == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值，在最大贡献值大于 0 时才会选取对应子节点。
        int leftMax = Math.max(maxPathSumOn(root.left, ret), 0),
            rightMax = Math.max(maxPathSumOn(root.right, ret), 0);

        // 更新答案：root 的最大路径和为 root.val 与其左右子节点最大路径和之和。
        ret[0] = Math.max(ret[0], root.val + leftMax + rightMax);

        // 返回节点的最大贡献值：取左右路径中和更大的一条路径，加上当前节点值，退递归。
        //   1
        //  / \
        // 4   8
        //      1 + 8 == 9 这条路径用于计算下一个路径和。
        return root.val + Math.max(leftMax, rightMax);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public int maxPathSumOn(TreeNode root) {

        // 数组便于传参，由于节点值可能为负数，所以每次求值都需要把结果与 0 比较取较大者（表示不累计负值）。
        int[] ret = new int[]{Integer.MIN_VALUE};
        maxPathSumOn(root, ret);
        return ret[0];
    }
}
