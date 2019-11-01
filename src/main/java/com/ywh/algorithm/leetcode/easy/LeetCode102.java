package com.ywh.algorithm.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> elem = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                elem.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(elem);
        }
        return result;
    }
}
