package com.ywh.problem.leetcode.hard;

import com.ywh.model.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 合并 K 个有序链表
 * [链表] [分治] [堆]
 *
 * @author ywh
 * @since 11/11/2019
 */
public class LeetCode23 {

    /**
     * 归并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    private ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return dummy.next;
    }

    /**
     * 逐个归并链表
     * <p>
     * Time: O(k*n), Space: O(1)
     *
     * @param lists
     * @return
     */
    public ListNode mergeKSortedListsOneByOne(ListNode[] lists) {
        ListNode result = null;
        if (lists == null || lists.length == 0) {
            return null;
        }
        for (ListNode list : lists) {
            result = mergeTwoSortedLists(result, list);
        }
        return result;
    }

    /**
     * 最小堆解法：
     * 把链表列表添加到最小堆（判断头节点）；
     * 每次从最小堆中获取头节点最小的链表，截取头部加入结果链表；
     * 如果出堆元素不是链表结尾，则把后续部分重新入堆；
     * 循环直到堆空即可
     * <p>
     * Time: O(n*log(k)), Space: O(k)
     *
     * @param lists
     * @return
     */
    public ListNode mergeKSortedListsMinHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 最小堆（优先级队列）：每次从中获取最小元素，在这里表示头节点值最小的链表
        Queue<ListNode> q = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode list : lists) {
            if (list != null) {
                q.add(list);
            }
        }

        ListNode dummy = new ListNode(0), p = dummy, min;
        while (!q.isEmpty()) {
            min = q.poll();
            p.next = min;
            p = p.next;
            if (min.next != null) {
                q.add(min.next);
            }
        }
        return dummy.next;
    }

    /**
     * @param lists
     * @param start
     * @param end
     * @return
     */
    private ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        ListNode left = merge(lists, start, mid);
        ListNode right = merge(lists, mid + 1, end);
        return mergeTwoSortedLists(left, right);
    }

    /**
     * 分治解法
     * <p>
     * Time: O(n*log(k)), Space: O(log(k))
     *
     * @param lists
     * @return
     */
    public ListNode mergeKSortedListsDivideConquer(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }
}
