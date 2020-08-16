package com.ywh.problem.leetcode.medium;

import com.ywh.model.ListNode;

/**
 * 两两交换链表中的节点
 * [链表]
 *
 * @author ywh
 * @since 27/03/2020
 */
public class LeetCode24 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param head
     * @return
     */
    public ListNode swapPairsRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairsRecursive(next.next);
        next.next = head;
        return next;
    }

    /**
     * 以 pre 为辅助，交换其下一个（first）和下下一个（second），每轮跳两个
     *
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode swapPairsIterative(ListNode head) {
        ListNode dummy = new ListNode(0, head), pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode first = pre.next, second = pre.next.next;
            pre.next = second;
            first.next = second.next;
            second.next = first;
            pre = first;
        }
        return dummy.next;
    }
}
