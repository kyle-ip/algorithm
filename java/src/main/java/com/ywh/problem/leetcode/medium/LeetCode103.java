package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.*;

/**
 * 二叉树的 Z 字形遍历
 * [树] [BFS] [队列]
 *
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 例如：
 *      给定二叉树 [3,9,20,null,null,15,7],
 *          3
 *         / \
 *        9  20
 *          /  \
 *         15   7
 *      返回锯齿形层序遍历如下：
 *      [
 *        [3],
 *        [20,9],
 *        [15,7]
 *      ]
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
            LinkedList<Integer> elem = new LinkedList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (right2Left) {
                    elem.addFirst(node.val);
                } else {
                    elem.addLast(node.val);
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
