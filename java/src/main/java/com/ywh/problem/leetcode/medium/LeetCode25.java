package com.ywh.problem.leetcode.medium;

import com.ywh.model.ListNode;

/**
 * K 个一组翻转链表
 * [链表]
 *
 * @author ywh
 * @since 2020/9/19 18:22
 */
public class LeetCode25 {

    /**
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0), prev = dummy;
        dummy.next = head;

        while (head != null) {
            ListNode tail = prev;
            // 检查剩余部分长度是否大于等于 k。
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                // 剩余部分不足 k，返回。
                if (tail == null) {
                    return dummy.next;
                }
            }
            ListNode next = tail.next;

            //
            //          |<-     k    ->|
            // [   ] .. [head] .. [tail] -> [next]

            ListNode[] group = reverseGroup(head, tail);
            head = group[0];
            tail = group[1];

            // 把子链表重新接回原链表：
            //                    3
            //                 [prev]
            //
            //       [head] .. [tail] (reversed)
            //         ↑          |
            //       1 |        +-+ 2
            //         |        |
            //         |        ↓
            //       [prev] -> [next] -> [    ]
            //
            //                 [head]
            //                   4
            prev.next = head;
            tail.next = next;
            prev = tail;
            head = next;
        }

        return dummy.next;
    }

    /**
     * 反转 head 与 tail 之间的链表，返回以 tail 为头、head 为尾的链表数组（元组）。
     *
     * @param head
     * @param tail
     * @return
     */
    public ListNode[] reverseGroup(ListNode head, ListNode tail) {
        ListNode cur = head, prev = tail.next, next;
        while (prev != null) {
            //         2
            //    +--------+
            //    ↓        |        1
            // [prev] -> [cur] -> [next]
            //
            //             3         4
            // [    ] <- [prev] -> [cur] -> [next]
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return new ListNode[]{tail, head};
    }

}