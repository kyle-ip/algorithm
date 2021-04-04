package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.*;

/**
 * 二叉树的层序遍历
 * [树] [广度优先搜索]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode102 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
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
            ret.add(elem);
        }
        return ret;
    }
}
