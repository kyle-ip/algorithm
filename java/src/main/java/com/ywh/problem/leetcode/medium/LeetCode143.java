package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 重排链表
 * [链表]
 *
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1:
 *      给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *      给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * @author ywh
 * @since 2020/10/15/015
 */
public class LeetCode143 {

    /**
     * 规律是 1 -> n-1 -> 2 -> n-2 -> 3 -> ...
     * 一次遍历利用数组存储链表节点，再次遍历时双指针取元素重排。
     *
     * Time: O(n), Space: O(n)
     * @param head
     */
    public void reorderListWithArray(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // 使用数组缓存链表节点，随后可用下标访问。
        List<ListNode> list = new ArrayList<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            list.add(cur);
        }

        // 双指针，一头一尾取数组元素，头++ -> 尾，尾-- -> 头。

        // 奇数
        //                   ③
        //        ①   +-------------+
        //     +------|-------------|------+
        //     |      |      x      ↓      ↓
        //    [1]    [2]    [3] -> [4]    [5]
        //            ↑      ↑      |      |
        //            +------|------|------+ ②
        //                   +------+
        //                       ④
        //
        //    1 -> 2 -> 3 -> 4 -> 5 -> null   =>    1 -> 5 -> 2 -> 4 -> 3 -> null

        // 偶数
        //               ③
        //        ①   +------+
        //     +------|------|------+
        //     |      |      ↓      ↓
        //    [1]    [2]    [3] -> [4]
        //            ↑      x      |
        //            +-------------+ ②
        //
        //    1 -> 2 -> 3 -> 4 -> null    =>    1 -> 4 -> 2 -> 3 -> null

        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i++).next = list.get(j);
            list.get(j--).next = list.get(i);
        }

        // 最终要断开下标交汇点，表示重组链表的结尾。
        list.get(i).next = null;
    }

    public void reorderListRecursive(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        int len = 0;
        ListNode h = head;
        // 求出节点数。
        while (h != null) {
            len++;
            h = h.next;
        }

        reorderListRecursive(head, len);
    }

    private ListNode reorderListRecursive(ListNode head, int len) {
        if (len == 1) {
            ListNode outTail = head.next;
            head.next = null;
            return outTail;
        }
        if (len == 2) {
            ListNode outTail = head.next.next;
            head.next.next = null;
            return outTail;
        }

        // 得到对应的尾节点，并且将头节点和尾节点之间的链表通过递归处理。
        ListNode tail = reorderListRecursive(head.next, len - 2);

        // 中间链表的头节点。
        ListNode subHead = head.next;
        head.next = tail;

        // 上一层 head 对应的 tail。
        ListNode outTail = tail.next;
        tail.next = subHead;
        return outTail;
    }

    /**
     * 把链表从中点截断成两部分，后一部分反转，然后重组两个链表。
     *
     * Time: O(n), Space: O(1)
     * @param head
     */
    public void reorderListReverse(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        // 找中点，把链表断开成两个：
        // [1] -> [2] -> [3] -> [4]    =>    [1] -> [2]    [3] -> [4]
        //       slow   fast                       slow   newHead
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;

        // 将第二个链表倒置：倒置以后原来的 newHead 变成尾节点，prev 变成头节点，因此最后要重置头节点。
        //   [3] -> [4]    =>    [3] <- [4]    =>    [4] -> [3]
        // newHead             newHead  prev       newHead
        ListNode cur = newHead, prev = null, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        newHead = prev;

        // 链表节点依次连接：
        //                                                                      3
        //               2                                    2     +------------------------+
        //    +----------------------+   1              +-----------|----------+   1         |
        //    |    +-----------------|------+           |    +------|----------|------+      |
        //    |    |                 |      ↓           |    |      |          |      ↓      ↓
        //    +-> [4]    [3]        [1]    [2]    =>    +-> [4]    [3]        [1]    [2]    null
        //    newHead => next      head => head                     ↑        4        |
        //                                                          +-----------------+
        //
        //    [1] -> [4] -> [2] -> [3] -> null
        while (newHead != null) {
            next = newHead.next;
            newHead.next = head.next;
            head.next = newHead;
            head = newHead.next;
            newHead = next;
        }
    }
}
