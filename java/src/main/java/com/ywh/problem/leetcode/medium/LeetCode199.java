package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.*;

/**
 * 二叉树的右视图
 * [树] [广度优先搜索] [深度优先搜索]
 *
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 示例:
 *      输入: [1,2,3,null,5,null,4]
 *      输出: [1, 3, 4]
 *      解释:
 *
 *         1            <---
 *       /   \
 *      2     3         <---
 *       \     \
 *        5     4       <---
 *
 * @author ywh
 * @since 28/04/2020
 */
public class LeetCode199 {

    /**
     * 层序遍历，把每层从右到左。
     *
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideViewBFS(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> ret = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            // 在队列中遍历每层都只取头部元素（即每层最右边的值），注意此时不要出队。
            ret.add(q.peek().val);
            int size = q.size();
            for (int i = 0; i < size; i++) {
                root = q.poll();

                // 每次先放入右边。
                if (root.right != null) {
                    q.add(root.right);
                }
                if (root.left != null) {
                    q.add(root.left);
                }
            }
        }
        return ret;
    }

    /**
     * 深度优先，根、右、左
     *
     * @param root
     * @param ret
     * @param level
     */
    private void dfs(TreeNode root, List<Integer> ret, int level) {
        if (root == null) {
            return;
        }
        // level == size 表示访问到新的一层（该层未被访问）
        if (level == ret.size()) {
            ret.add(root.val);
        }
        dfs(root.right, ret, level + 1);
        dfs(root.left, ret, level + 1);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        dfs(root, ret, 0);
        return ret;
    }

}
