package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

/**
 * 单链表排序
 * [链表] [排序]
 *
 * @author ywh
 * @since 2/21/2019
 */
public class LeetCode148 {

    /**
     * 交换两个链表的值
     *
     * @param a
     * @param b
     */
    private void swap(ListNode a, ListNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    /**
     *
     * @param start
     * @param end
     */
    private void quickSort(ListNode start, ListNode end) {
        if (start == end) {
            return;
        }

        // 以 start 为 pivot：每轮循环后 left 指向 [start, right] 中最后一个比 start 小的元素。

        // 1: right 比 start 小，移动 left。
        //      [3] -> [1] -> [4] -> [2] -> [ ]
        //     left   right

        // 2: 交换 right、left 所指的值。
        //            swap()
        //              ↓
        //      [3] -> [1] -> [4] -> [2] -> [ ]
        //            right
        //            left

        // 3: right 比 start 大，跳过。
        //      [3] -> [1] -> [4] -> [2] -> [ ]
        //            left   right

        // 4: right 比 start 小，移动 left（刚才被跳过，即移动后的 left 是比 start 大的），交换 right、left 所指的值。
        //                      swap()
        //                     +------+
        //                     ↓      ↓
        //      [3] -> [1] -> [4] -> [2] -> [ ]    =>    [3] -> [1] -> [2] -> [4] -> [ ]
        //                   left   right                             left          right
        ListNode left = start, right = start.next;
        for (;right != end; right = right.next) {
            if (right.val <= start.val) {
                left = left.next;
                swap(left, right);
            }
        }

        // 5: right 停止移动时 left 所在的位置把 [start + 1, right) 分成两半，左边（包括 left 自身）都小于等于 start，右边都大于 start。
        // 交换 start、left 所指的值，使 [start, right) 以 left 为界，左边都小于它，右边（包括 left 自身）都大于等于它。
        //            swap()
        //       +-------------+                                 分成两半
        //       ↓             ↓                         |<-           ->|<-  ->|
        //      [3] -> [1] -> [2] -> [4] -> [ ]    =>    [2] -> [1] -> [3] -> [4] -> [ ]
        //                   left                                     left

        swap(start, left);
        quickSort(start, left);
        quickSort(left.next, end);
    }

    /**
     * 快速排序法
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param head
     * @return
     */
    public ListNode quickSortList(ListNode head) {
        quickSort(head, null);
        return head;
    }

    /**
     * 归并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    private ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), cur = dummy;
        for (;l1 != null && l2 != null; cur = cur.next) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
        }
        cur.next = l1 != null? l1: l2;
        return dummy.next;
    }

    /**
     * 归并排序法
     * Time: O(n*log(n)), Space: O(log(n))
     *
     * @param head
     * @return
     */
    public ListNode mergeSortList(ListNode head) {
        // 如果传入空值或只有一个节点，则无需排序。
        if (head == null || head.next == null) {
            return head;
        }

        // 左右两个指针，分别移动到链表的中点和终点。
        // 1:
        //      [3] -> [1] -> [4] -> [2] -> null
        //     fast
        //     slow

        // 2:
        //      [3] -> [1] -> [4] -> [2] -> null
        //                   fast
        //            slow
        ListNode slow = head, fast = head, left, right;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 3: 以 slow 为界，把链表切分两段（slow 在左边），先递归处理右边。
        //      (  left  )    (  right )
        //      [3] -> [1] -> [4] -> [2] -> null
        //            slow
        right = mergeSortList(slow.next);

        // 4: 处理完右边后，要修改 slow 指向，断成两个链表。
        //      (  left  )          (  right )
        //      [3] -> [1] -> null, [2] -> [4] -> null
        //     head   slow
        slow.next = null;

        // 5: 处理左边，此后得到两个独立的有序链表。
        //      (  left  )          (  right )
        //      [1] -> [3] -> null, [2] -> [4] -> null
        //     head   slow
        left = mergeSortList(head);

        // 6: 合并两个有序链表。
        // [1] -> [3] -> null, [2] -> [4] -> null    =>    [1] -> [2] -> [3] -> [4] -> null
        return mergeTwoSortedLists(left, right);
    }
}
