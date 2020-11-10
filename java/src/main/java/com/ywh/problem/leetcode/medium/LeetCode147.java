package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

/**
 * 单链表插入排序
 * [链表] [排序]
 *
 * @author ywh
 * @since 12/01/2020
 */
public class LeetCode147 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0), p;
        ListNode cur = head, next;
        while (cur != null) {
            next = cur.next;
            p = dummy;
            while (p.next != null && cur.val >= p.next.val) {
                p = p.next;
            }
            cur.next = p.next;
            p.next = cur;
            cur = next;
        }
        return dummy.next;
    }

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode insertionSortListFast(ListNode head) {
        ListNode dummy = new ListNode(0), p = dummy;
        ListNode cur = head, next;
        while (cur != null) {
            next = cur.next;
            if (p.next != null && cur.val < p.next.val) {
                p = dummy;
            }
            while (p.next != null && cur.val >= p.next.val) {
                p = p.next;
            }
            cur.next = p.next;
            p.next = cur;
            cur = next;
        }
        return dummy.next;
    }

}
