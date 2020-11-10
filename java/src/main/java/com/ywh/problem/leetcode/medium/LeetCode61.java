package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

/**
 * 旋转单链表
 * [链表] [双指针]
 *
 * @author ywh
 * @since 2019/2/21
 */
public class LeetCode61 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRightToLeft(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }

        // 计算链表长度、把链表尾首相连成环
        ListNode end = head;
        int n = 1;
        while (end.next != null) {
            end = end.next;
            n++;
        }
        end.next = head;

        // 新建尾指针，定位到链表的倒数第 k 位，则新链表头为新尾指针的下一个节点
        k %= n;
        ListNode newEnd = head;
        for (int i = 0; i < n - k - 1; i++) {
            newEnd = newEnd.next;
        }
        ListNode newHead = newEnd.next;

        // 最后把链表的尾指针置空，解环
        newEnd.next = null;

        return newHead;
    }

}
