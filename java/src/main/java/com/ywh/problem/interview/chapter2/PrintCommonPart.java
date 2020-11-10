package com.ywh.problem.interview.chapter2;

import com.ywh.ds.list.ListNode;

/**
 * 打印两个有序链表的公共部分
 * [链表]
 *
 * @author ywh
 * @since 11/10/2020
 */
public class PrintCommonPart {

    /**
     * 每轮循环移动较小者，相等则输出并同时移动。
     * @param head1
     * @param head2
     */
    public void printCommonPart(ListNode head1, ListNode head2) {
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                head1 = head1.next;
            } else if (head1.val > head2.val) {
                head2 = head2.next;
            } else {
                System.out.println(head1.val);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

}
