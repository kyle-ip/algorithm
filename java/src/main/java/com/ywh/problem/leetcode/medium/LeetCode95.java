package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用 1~n 生成二叉搜索树
 * [树] [动态规划]
 *
 * @author ywh
 * @since 01/04/2020
 */
public class LeetCode95 {

    /**
     *
     * @param root
     * @return
     */
    private TreeNode cloneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        return new TreeNode(root.val, cloneTree(root.left), cloneTree(root.right));
    }

    /**
     *
     * @param low
     * @param high
     * @return
     */
    private List<TreeNode> generateTrees(int low, int high) {
        if (low > high) {
            return Collections.singletonList(null);
        }
        if (low == high) {
            return Collections.singletonList(new TreeNode(low));
        }
        List<TreeNode> ret = new ArrayList<>();
        for (int i = low; i <= high; i++) {
            List<TreeNode> left = generateTrees(low, i - 1), right = generateTrees(i + 1, high);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    ret.add(new TreeNode(i, cloneTree(l), cloneTree(r)));
                }
            }
        }
        return ret;
    }

    /**
     * Time: O(4^n / n^(3/2)), Space: O(4^n / n^(3/2))     第 n 项卡特兰数
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return Collections.emptyList();
        }
        return generateTrees(1, n);
    }
}
