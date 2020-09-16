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
     *
     * @param root
     * @param max
     * @return
     */
    private int maxPathSumFromRootAndCompute(TreeNode root, int[] max) {
        //      -4
        //    /  \
        //   1    2
        //  / \
        // 4   8

        if (root == null) {
            return 0;
        }
        // 取左边、右边的最大值，与当前值相加，得出路径和（最大值保存在 max）。
        int leftMax = Math.max(maxPathSumFromRootAndCompute(root.left, max), 0);
        int rightMax = Math.max(maxPathSumFromRootAndCompute(root.right, max), 0);
        max[0] = Math.max(max[0], root.val + leftMax + rightMax);

        // 返回较大的一条路径：
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
        maxPathSumFromRootAndCompute(root, max);
        return max[0];
    }
}
