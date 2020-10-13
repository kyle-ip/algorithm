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
     * 递归解法，结束条件为最后不足两个节点。
     *
     * Time: O(n), Space: O(n)
     *
     * @param head
     * @return
     */
    public ListNode swapPairsRecursive(ListNode head) {
        // [1] -> [2] -> [3] -> [4] -> [5] -> [6] -> null

        // 最底层的递归栈帧：head == [5]
        // next = head.next => [5].next => [6]
        // head.next => [5].next = f([6].next) => f(null) => null
        // next.next = [6].next => [5]
        //                                      [1] -> [2] -> [3] -> [4] -> [6] -> [5] -> null
        // return: [6] ---------------------------------------------+
        //                                                          |
        // 其上一层的栈帧：head == [4]                              |
        // next = head.next => [4].next => [5]                      |
        // head.next => [4].next = f([5].next) => f([6]]) <---------+
        // next.next => [5].next => [4]
        // return: [5]
        // ...

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
            //      pre   first  second
            //                     2
            //           1      +---------------------+
            //       +------ ---|-----------+         |
            //       |          |           ↓         ↓
            //      [pre] -> [first] -> [second] -> [   ] -> [   ] -> null
            //                  ↑          |
            //                  +----------+
            //                       3
            ListNode first = pre.next, second = pre.next.next;
            pre.next = second;
            first.next = second.next;
            second.next = first;
            pre = first;
        }
        return dummy.next;
    }
}
