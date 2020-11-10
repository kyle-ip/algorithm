package com.ywh.problem.interview.chapter2;

import com.ywh.ds.list.ListNode;

/**
 * 删除链表的中间节点和 a/b 处的节点
 * [链表]
 *
 * @author ywh
 * @since 11/10/2020
 */
public class RemoveByRatio {

    public ListNode removeByRatio(ListNode head, int a, int b) {
        if (a < 1 || a > b) {
            return head;
        }

        // 计算链表长度，设 n = 7，a = 5，b = 7。
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        // n = 5 * 7 / 7 == 5
        n = (int) Math.ceil((double) (a * n) / (double) b);
        if (n == 1) {
            return head.next;
        }
        if (n < 1) {
            return head;
        }
        // k     5      4      3      2      1
        //      [ ] -> [ ] -> [ ] -> [ ] -> [x] -> [ ] -> [ ] -> null
        cur = head;
        while (--n != 1) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return head;
    }


    public ListNode removeMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 链表长度 = 2。
        //       return
        // [ ] -> [ ] -> null
        if (head.next.next == null) {
            return head.next;
        }

        // 链表长度 > 2，让 fast 先领先 slow 2 个单位，且以两倍于 slow 的速度前进、当距离终点不足两个单位时停下。
        ListNode fast = head.next.next, slow = head;

        // 当链表长度为奇数时：
        //               fast                                                                  fast
        // slow                                             ===>          slow
        // [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> null                 [ ] -> [ ] -> [x] -> [ ] -> [ ] -> null

        // 当链表长度为偶数时：
        //               fast                                                                  fast
        // slow                                             ===>          slow
        // [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> null          [ ] -> [ ] -> [x] -> [ ] -> [ ] -> [ ] -> null
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 最终 slow 停留在中点的前一个位置，移除其 next 即可。
        slow.next = slow.next.next;
        return head;
    }

}
