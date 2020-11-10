package com.ywh.problem.leetcode.easy;

import com.ywh.ds.list.ListNode;

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
            return head;
        }
        for (ListNode cur = head; cur.next != null;) {

            //  +-------------+
            //  |             |
            // cur            ↓
            // [1] -> [1] -> [2] -> [3] -> null
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            }

            //        cur => cur
            // [1] -> [1] -> [2] -> [3] -> null
            else {
                cur = cur.next;
            }
        }
        return head;
    }
}
