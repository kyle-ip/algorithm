package com.ywh.problem.leetcode.hard;

import com.ywh.model.TreeNode;

/**
 * 二叉树的最大路径和
 * [树] [DFS]
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
        int leftMax = Math.max(maxPathSumFrom(root.left), 0);
        int rightMax = Math.max(maxPathSumFrom(root.right), 0);
        return root.val + Math.max(leftMax, rightMax);
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
        int leftMax = Math.max(maxPathSumFrom(root.left), 0);
        int rightMax = Math.max(maxPathSumFrom(root.right), 0);
        return max(
                root.val + leftMax + rightMax,
                maxPathSumBruteForce(root.left),
                maxPathSumBruteForce(root.right)
        );
    }

    /**
     * @param root
     * @param max
     * @return
     */
    private int maxPathSumOn(TreeNode root, int[] max) {
        //     -4
        //    /  \
        //   1    2
        //  / \
        // 4   8

        if (root == null) {
            return 0;
        }
        // 递归计算左右子节点的最大贡献值，在最大贡献值大于 0 时才会选取对应子节点。
        int leftMax = Math.max(maxPathSumOn(root.left, max), 0), rightMax = Math.max(maxPathSumOn(root.right, max), 0);

        // 更新答案：节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值。
        max[0] = Math.max(max[0], root.val + leftMax + rightMax);

        // 返回节点的最大贡献值：
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

        // 数组便于传参。
        int[] max = new int[]{Integer.MIN_VALUE};
        maxPathSumOn(root, max);
        return max[0];
    }
}
