package com.ywh.problem.leetcode.medium;

import com.ywh.model.ListNode;

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
        if (start == end || start.next == end) {
            return;
        }

        int pivot = start.val;
        ListNode left = start, right = start.next;

        // 由于 right 每遇到值比 pivot 小的节点都会与 left 的后一个元素（即一个比 pivot 大的值，把它换到右边去）交换
        // （如果 right 和 left 仅相隔一个单位时，不发生交换）
        // 因此最终 right 停止移动时，left 所指的位置，左边都小于等于 pivot，右边都小于 pivot
        while (right != end) {
            if (right.val <= pivot) {
                left = left.next;
                swap(left, right);
            }
            right = right.next;
        }

        // 把 pivot 位置的值换到中间，使其左边节点的值都小于它，右边节点的值都大于它
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
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null? l1: l2;
        return null;
    }

    /**
     * 归并排序法
     * Time: O(n*log(n)), Space: O(log(n))
     *
     * @param head
     * @return
     */
    public ListNode mergeSortList(ListNode head) {
        // 如果传入空值或只有一个节点，则无需排序
        if (head == null || head.next == null) {
            return head;
        }

        // 左右两个指针，分别移动到链表的中点和终点
        ListNode slow = head, fast = head, left, right;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 把链表切分两段，递归调用划分
        right = mergeSortList(slow.next);
        slow.next = null;
        left = mergeSortList(head);

        // 合并两个有序链表
        return mergeTwoSortedLists(left, right);
    }
}
