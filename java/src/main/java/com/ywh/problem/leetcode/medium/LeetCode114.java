package com.ywh.problem.leetcode.medium;

import com.ywh.model.ListNode;
import com.ywh.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 拍平二叉树
 * [树] [DFS]
 *
 * @author ywh
 * @since 21/11/2019
 */
public class LeetCode114 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     */
    public void flattenPreorder(TreeNode root) {
        if (root == null) {
            return;
        }
        // 求树的先序遍历列表：
        //         1
        //        / \
        //       2   5      [1, 2, 3, 4, 5, 6]
        //      / \   \
        //     3   4   6
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> preorderList = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                preorderList.add(cur);
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop().right;
            }
        }
        // 从第二个节点开始遍历该列表，逐个取出并插入到根节点的右边，且在遍历过程中把左节点置空。
        //     1
        //    / \
        //   x   2
        //      / \
        //     x   3
        //          ...
        for (int i = 1; i < preorderList.size(); i++) {
            root.left = null;
            root.right = preorderList.get(i);
            root = root.right;
        }
    }

    private TreeNode prev = null;

    /**
     * 反向前序遍历的递归调用
     *
     * @param root
     */
    public void flattenReversePreorder(TreeNode root) {
        //             [1]
        //            /   \
        //         [2]     [5]
        //        /   \       \
        //     [3]     [4]     [6]

        if (root == null) {
            return;
        }
        flattenReversePreorder(root.right);
        flattenReversePreorder(root.left);

        // 一直递归，到底后设置右节点为上一个记录的节点，重新记录 prev：
        root.left = null;
        root.right = prev;
        prev = root;
    }

}
