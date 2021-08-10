package com.ywh.problem.leetcode.hard;

import com.ywh.ds.list.ListNode;

/**
 * K 个一组翻转链表
 * [链表] [递归]
 * 
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 进阶：
 *      你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 *      你不能只是单纯地改变节点内部的值，而是需要实际进行节点交换。
 * 示例 1：
 *      输入：head = [1,2,3,4,5], k = 2
 *      输出：[2,1,4,3,5]
 * 示例 2：
 *      输入：head = [1,2,3,4,5], k = 3
 *      输出：[3,2,1,4,5]
 * 示例 3：
 *      输入：head = [1,2,3,4,5], k = 1
 *      输出：[1,2,3,4,5]
 * 示例 4：
 *      输入：head = [1], k = 1
 *      输出：[1]
 * 提示：
 *      列表中节点的数量在范围 sz 内
 *      1 <= sz <= 5000
 *      0 <= Node.val <= 1000
 *      1 <= k <= sz
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
        ListNode tail = head;
        // 不足 k 个直接返回。
        for (int i = 0; i < k; i++, tail = tail.next) {
            if (tail == null) {
                return head;
            }
        }
        // 反转 head 与 tail 之间的一段，作为新头指针。
        ListNode newHead = reverse(head, tail);
        head.next = reverseKGroup(tail, k);

        // 最后返回新头指针。
        return newHead;
    }

    /**
     *
     * @param head
     * @param tail
     * @return
     */
    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode prev = tail;
        while (head != tail) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head, int k) {

        // dummy 表示 head 前的辅助节点，prev 表示待反转子链表的前驱节点， left、right 表示待反转子链表的左右边界（闭区间）。
        ListNode dummy = new ListNode(0, head), prev = dummy, left = head, right;

        // k = 3
        // dummy -> [1] -> [2] -> [3] -> [4] -> [5] -> [6] -> [7] -> null
        while (left != null) {

            // 检查从 tail 开始的剩余部分长度是否大于等于 k。
            //          |<-     k     ->|
            // dummy -> [1] -> [2] -> [3] -> ...
            //          right   =>    right
            right = prev;
            for (int i = 0; i < k; i++) {
                right = right.next;
                // 剩余部分不足 k，返回。
                if (right == null) {
                    return dummy.next;
                }
            }

            // 保存待反转子链表的后继节点。
            //          |<-     k     ->|
            // dummy -> [1] -> [2] -> [3] -> [4] -> [5]
            // prev    left          right   next
            ListNode next = right.next;

            // 反转长度为 k 的子链表。
            //          |<-     k     ->|
            // dummy -> [1] <- [2] <- [3] -> [4] -> [5]
            // prev    right          left   next
            ListNode[] group = reverseGroup(left, right);
            left = group[0];
            right = group[1];

            // 把反转后的子链表重新接回原链表。
            //                prev
            //                dummy ---+
            //                         ↓
            //          [1] <- [2] <- [3]    [4] -> [5]
            //          tail          left   next
            prev.next = left;

            //           +--------------------+
            //           |    dummy ---+      |
            //           |             ↓      ↓
            //          [1] <- [2] <- [3]    [4] -> [5]
            //         right          cur    next
            right.next = next;

            // 移动 prev 和 head。
            //           +--------------------+
            //           |    dummy ---+      |
            //           |             ↓      ↓
            //          [1] <- [2] <- [3]    [4] -> [5]
            //          prev          cur    left
            prev = right;
            left = next;
        }

        return dummy.next;
    }

    /**
     * 反转 head 与 tail 之间的链表，返回以 tail 为头、head 为尾的链表数组（元组）。
     *
     * @param left
     * @param right
     * @return
     */
    public ListNode[] reverseGroup(ListNode left, ListNode right) {
        //     [ ] -> [1] -> [2] -> [3] -> [ ]
        //            cur                  prev
        ListNode cur = left, prev = right.next, next;
        while (prev != right) {

            //     [ ] -> [1] -> [2] -> [3] -> [ ]
            //            cur    next          prev

            //             +--------------------+
            //             |                    ↓
            //     [ ] -> [1]    [2] -> [3] -> [ ]
            //            cur    next          prev

            //             +--------------------+
            //             |                    ↓
            //     [ ] -> [1]    [2] -> [3] -> [ ]
            //            prev

            //             +--------------------+
            //             |                    ↓
            //     [ ] -> [1] <- [2]    [3] -> [ ]
            //            prev   cur    next   prev

            //             +--------------------+
            //             |                    ↓
            //     [ ] -> [1] <- [2]    [3] -> [ ]
            //                   prev   cur   next

            //             +--------------------+
            //             |                    ↓
            //     [ ] -> [1] <- [2] <- [3]    [ ]
            //                   prev   cur   next

            //             +--------------------+
            //             |                    ↓
            //     [ ] -> [1] <- [2] <- [3]    [ ]
            //                          prev   cur    (break)
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return new ListNode[]{right, left};
    }
}