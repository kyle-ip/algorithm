package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.*;

/**
 * 二叉树的 Z 字形遍历
 * [树] [BFS] [队列]
 *
 * @author ywh
 * @since 24/12/2019
 */
public class LeetCode103 {


    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean right2Left = false;
        while (!q.isEmpty()) {
            List<Integer> elem = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                elem.add(node.val);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            if (right2Left) {
                Collections.reverse(elem);
            }
            ret.add(elem);
            right2Left = !right2Left;
        }
        return ret;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrderV2(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean right2Left = false;
        while (!q.isEmpty()) {
            List<Integer> elem = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (right2Left) {
                    elem.add(0, node.val);
                } else {
                    elem.add(node.val);
                }
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            ret.add(elem);
            right2Left = !right2Left;
        }
        return ret;
    }
}
