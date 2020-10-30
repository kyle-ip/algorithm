package com.ywh.problem.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的逆层序遍历
 * [树] [BFS]
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
