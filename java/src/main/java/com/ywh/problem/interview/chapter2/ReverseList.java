package com.ywh.problem.interview.chapter2;

import com.ywh.ds.list.DoublyListNode;
import com.ywh.ds.list.ListNode;

/**
 * 反转单向和双向链表
 * [链表]
 *
 * @author ywh
 * @since 11/10/2020
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode cur = head, pre = null, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public DoublyListNode reverseList(DoublyListNode head) {
        DoublyListNode cur = head, pre = null, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            cur.prev = next;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
