package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;

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
     * 拷贝一棵二叉树（使树相互独立、不共享节点）
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
     * 输入两个整数，输出其之间的整数生成的二叉搜索树列表
     *
     * @param low
     * @param high
     * @return
     */
    private List<TreeNode> genTrees(int low, int high) {
        if (low > high) {
            return Collections.singletonList(null);
        }
        if (low == high) {
            return Collections.singletonList(new TreeNode(low));
        }
        List<TreeNode> ret = new ArrayList<>();
        // 遍历 [low, high]
        for (int i = low; i <= high; i++) {
            // 以 i 为根节点（的值），i 的左边为左子树、右边为右子树递归划分。
            List<TreeNode> left = genTrees(low, i - 1), right = genTrees(i + 1, high);
            // 遍历左右子树列表，取出一棵左子树、一棵右子树，组合成以 i 为根的新树，添加到结果集。
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
        return genTrees(1, n);
    }
}
