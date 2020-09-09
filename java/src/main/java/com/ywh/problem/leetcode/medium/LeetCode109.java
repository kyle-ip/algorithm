package com.ywh.problem.leetcode.medium;

import com.ywh.model.ListNode;
import com.ywh.model.TreeNode;

/**
 * 有序链表转换二叉搜索树
 * [DFS] [链表]
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
        ListNode mid = left, fast = left;
        for (; fast != right && fast.next != right; mid = mid.next, fast = fast.next.next);
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(left, mid);
        root.right = sortedListToBST(mid.next, right);
        return root;
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
        for (; head != null; len += 1, head = head.next) ;
        return sortedListToBST2(0, len - 1);
    }

}
