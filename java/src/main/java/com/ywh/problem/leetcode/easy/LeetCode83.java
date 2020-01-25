package com.ywh.problem.leetcode.easy;

import com.ywh.model.ListNode;

/**
 * 有序链表去重
 * [链表]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode83 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicatesInSortedList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head, next = head.next;
//        while (cur != null) {
//            while (cur.next != null && cur.val == cur.next.val) {
//                cur.next = cur.next.next;
//            }
//            cur = cur.next;
//        }
        while (next != null) {
            if (cur.val == next.val) {
                cur.next = next.next;
            } else {
                cur = cur.next;
            }
            next = next.next;
        }
        return head;
    }
}
