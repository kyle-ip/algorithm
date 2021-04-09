package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树展开为链表
 * [树] [深度优先搜索]
 * 
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * 示例 1：
 *      输入：root = [1,2,5,3,4,null,6]
 *      输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 *      输入：root = []
 *      输出：[]
 * 示例 3：
 *      输入：root = [0]
 *      输出：[0]
 * 提示：
 *      树中结点数在范围 [0, 2000] 内
 *      -100 <= Node.val <= 100
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
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
        // 求树的前序遍历列表：
        //         1
        //        / \
        //       2   5      [1, 2, 3, 4, 5, 6]
        //      / \   \
        //     3   4   6
        Deque<TreeNode> stack = new LinkedList<>();
        List<TreeNode> preorder = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                preorder.add(cur);
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
        for (int i = 1; i < preorder.size(); i++) {
            root.left = null;
            root.right = preorder.get(i);
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
