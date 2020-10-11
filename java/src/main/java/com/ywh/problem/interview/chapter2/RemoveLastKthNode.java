package com.ywh.problem.interview.chapter2;

import com.ywh.model.DoublyListNode;
import com.ywh.model.ListNode;

/**
 * 在单链表和双链表中删除倒数第 k 个节点
 * [链表]
 *
 * @author ywh
 * @since 11/10/2020
 */
public class RemoveLastKthNode {

    public DoublyListNode removeLastKthNode(DoublyListNode head, int k) {
        if (head == null || k < 1) {
            return null;
        }
        DoublyListNode cur = head;
        for (; cur != null; cur = cur.next, k--);
        if (k == 0) {
            head.next.prev = null;
            return head.next;
        }
        if (k > 0) {
            return head;
        }
        cur = head;
        while (++k != 0) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        cur.next.prev = cur;
        return head;
    }

    public ListNode removeLastKthNode(ListNode head, int k) {
        if (head == null || k < 1) {
            return null;
        }
        ListNode cur = head;

        // 计数器先减后加，设链表长度为 n，则最终 k 被减为：k - n（最终 cur 指向 null，即 k 共需要减 n 次）
        // 设 n = 5, k = 2：
        // k        2      1      0      -1     -2     -3
        // list    [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> null
        for (; cur != null; cur = cur.next, k--) ;

        // 两种情况（k 大于 n 不用管），如果 k 正好等于 k，则删除头节点（返回头节点的 next）；
        // 如果 k 小于链表长度，则把 k 重新加到 0，正好定位到待删除元素的前一位，修改其指向即可。
        if (k == 0) {
            return head.next;
        }
        if (k > 0) {
            return head;
        }


        // k = -3
        // k        -3     -2     -1     0
        // list    [ ] -> [ ] -> [ ] -> [x] -> [ ] -> null
        cur = head;
        while (++k != 0) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return head;
    }

}
