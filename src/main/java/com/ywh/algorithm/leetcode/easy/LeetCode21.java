package com.ywh.algorithm.leetcode.easy;

import com.ywh.model.ListNode;

/**
 * 合并两个有序链表
 * [链表]
 *
 * @author ywh
 * @since 2/15/2019
 */
public class LeetCode21 {

    /**
     * Time: O(m+n), Space: O(1)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return dummy.next;
    }

}
