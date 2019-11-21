package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 拍平二叉树
 * [树] [DFS]
 *
 * @author ywh
 * @since 21/11/2019
 */
public class LeetCode114 {

    /**
     * 前序遍历
     *
     * @param root
     * @param list
     */
    private void preorder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     */
    public void flattenPreorder(TreeNode root) {
        if (root == null) {
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        preorder(root, list);
        TreeNode cur = root;
        for (int i = 1; i < list.size(); i++) {
            cur.left = null;
            cur.right = list.get(i);
            cur = cur.right;
        }
    }

    private TreeNode prev = null;

    /**
     * 反向前序遍历的递归调用
     *
     * @param root
     */
    public void flattenReversePreorder(TreeNode root) {
        if (root == null) {
            return;
        }
        flattenReversePreorder(root.right);
        flattenReversePreorder(root.left);

        // 一直递归，到底后设置右节点为上一个记录的节点，重新记录 prev
        root.left = null;
        root.right = prev;
        prev = root;
    }

}
