package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

/**
 * 奇偶链表
 * [链表]
 *
 * @author ywh
 * @since 09/12/2019
 */
public class LeetCode328 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode evenHead = head.next, even = evenHead, odd = head;
        while (even != null && even.next != null) {
            // 先修改后继指针，再修改自身，不断交错执行
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        // 奇链表的末尾指向偶链表的开头
        odd.next = evenHead;
        return head;
    }
}
