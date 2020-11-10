package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.DoublyListNode;
import com.ywh.ds.tree.TreeNode;

import java.util.Stack;

/**
 * 将二叉搜索树转化为排序的双向链表
 * [树] [链表]
 *
 * @author ywh
 * @since 2020/10/15/015
 */
public class LeetCode426 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public static DoublyListNode bstToDoublyList(TreeNode root) {
        DoublyListNode dummy = new DoublyListNode(-1), cur = dummy, next;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                next = new DoublyListNode(root.val);
                cur.next = next;
                next.prev = cur;
                cur = cur.next;
                root = root.right;
            }
        }
        return dummy.next;
    }
}
