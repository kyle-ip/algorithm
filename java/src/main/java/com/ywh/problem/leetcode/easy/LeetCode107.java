package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历 II
 * [树] [广度优先搜索]
 *
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 例如：
 *      给定二叉树 [3,9,20,null,null,15,7]
 *          3
 *         / \
 *        9  20
 *          /  \
 *         15   7
 *      返回其自底向上的层序遍历为：
 *      [
 *        [15,7],
 *        [9,20],
 *        [3]
 *      ]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode107 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderTraversalFromBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> elem = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 保存当前行每个元素
                elem.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // 保存每行元素
            ret.add(elem);
        }

        // 交换行（要求逆层序）
        for (int i = 0, j = ret.size() - 1; i < j; i++, j--) {
            List<Integer> elem = ret.get(j);
            ret.set(j, ret.get(i));
            ret.set(i, elem);
        }
        return ret;
    }
}
