package com.ywh.problem.interview.chapter2;

import com.ywh.model.ListNode;

/**
 * 反转部分单向链表
 * [链表]
 *
 * @author ywh
 * @since 12/10/2020
 */
public class ReversePart {

    /**
     * @param head
     * @param from
     * @param to
     * @return
     */
    public ListNode reversePart(ListNode head, int from, int to) {

        // 参数越界，直接返回。
        if (from > to || from < 1) {
            return head;
        }

        // 记录链表的长度。
        int len = 0;
        // left -> node1 -> ...（待反转的部分）-> right
        ListNode node1 = head, left = null, right = null, node2 = null, next = null;

        // 定位 left 和 right。
        while (node1 != null) {
            len++;
            if (len == from - 1) {
                left = node1;
            }
            if (len == to + 1) {
                right = node1;
            }
            node1 = node1.next;
        }

        // 参数越界，直接返回。
        if (to > len) {
            return head;
        }

        // 从第一个节点开始反转：from = 1，由于遍历过程中 len > from - 1 == 0，所以 left == null
        //       +-------------+
        //       ↓             ↓
        //      [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> null
        //     node1                right

        // 中间某段反转：
        //              +-------------+
        //              ↓             ↓
        //      [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> null
        //     left   node1                right
        node1 = left == null? head: left.next;

        // 反转 node2 到 right 之间：
        //                 （待反转）
        //              +-------------+
        //              ↓             ↓
        //      [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> null
        //     left   node1  node2          right
        //              |                    ↑
        //              +--------------------+
        node2 = node1.next;
        node1.next = right;
        while (node2 != right) {
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }

        //                 （已反转）
        //              +--------------------+
        //              |                    ↓
        //      [ ]    [ ] <- [ ] <- [ ]    [ ] -> [ ] -> null
        // left/head               node1 right/node2
        //      |                    ↑
        //      +--------------------+
        //   return
        if (left != null) {
            left.next = node1;
            return head;
        }

        //          （已反转）
        //       +-------------+
        //       ↓             ↓
        //      [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> null
        //                  node1 right/node2
        //                  return
        return node1;
    }

}
