package com.ywh.problem.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.*;

/**
 * 二叉树的层序遍历
 * [树] [BFS]
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
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> elem = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                elem.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ret.add(elem);
        }
        return ret;
    }
}
