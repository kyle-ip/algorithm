package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;
import com.ywh.ds.tree.TreeNode;

/**
 * 有序链表转换二叉搜索树
 * [深度优先搜索] [链表]
 *
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 示例:
 *      给定的有序链表： [-10, -3, 0, 5, 9],
 *      一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *                 0
 *                / \
 *              -3   9
 *              /   /
 *            -10  5
 *
 * @author ywh
 * @since 2020/9/9/009
 */
public class LeetCode109 {

    /**
     *
     * @param left
     * @param right
     * @return
     */
    private TreeNode sortedListToBST(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = left;
        for (ListNode p = left; p != right && p.next != right; mid = mid.next, p = p.next.next) {}
        return new TreeNode(
            mid.val, sortedListToBST(left, mid), sortedListToBST(mid.next, right)
        );
    }

    /**
     * Time: O(n*log(n)), Space: O(log(n))
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        return sortedListToBST(head, null);
    }

    ListNode cur;

    /**
     *
     * @param left
     * @param right
     * @return
     */
    public TreeNode sortedListToBST2(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = sortedListToBST2(left, mid - 1);
        root.val = cur.val;
        cur = cur.next;
        root.right = sortedListToBST2(mid + 1, right);
        return root;
    }

    /**
     * Time: O(n), Space: O(log(n))
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST2(ListNode head) {
        cur = head;
        int len = 0;
        for (; head != null; len += 1, head = head.next) {}
        return sortedListToBST2(0, len - 1);
    }

}
