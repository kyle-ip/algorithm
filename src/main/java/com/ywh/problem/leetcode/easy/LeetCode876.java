package com.ywh.problem.leetcode.easy;

import com.ywh.model.ListNode;

/**
 * 单链表中间节点
 * [链表]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode876 {

    /**
     * 两次遍历，第一次计算出链表长度，第二次移动到中间位置即返回
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode getMiddleNodeTwoPass(ListNode head) {
        int len = 0;
        for (ListNode cur = head; cur != null; len++, cur = cur.next);
        ListNode cur = head;
        for (int i = 0; i < len / 2; cur = cur.next, i++);
        return cur;
    }

    /**
     * 一次遍历，使用两个指针，快指针每次走两步，慢指针每次走一步；
     * 当快指针走到尽头即返回慢指针的节点
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode getMiddleNodeOnePass(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
