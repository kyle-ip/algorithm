package com.ywh.problem.leetcode.hard;

import com.ywh.ds.list.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 合并 K 个升序链表
 * [链表] [分治] [堆]
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 *      输入：lists = [[1,4,5],[1,3,4],[2,6]]
 *      输出：[1,1,2,3,4,4,5,6]
 *      解释：链表数组如下：
 *      [
 *        1->4->5,
 *        1->3->4,
 *        2->6
 *      ]
 *      将它们合并到一个有序链表中得到。
 *      1->1->2->3->4->4->5->6
 * 示例 2：
 *      输入：lists = []
 *      输出：[]
 * 示例 3：
 *      输入：lists = [[]]
 *      输出：[]
 *
 * 提示：
 *      k == lists.length
 *      0 <= k <= 10^4
 *      0 <= lists[i].length <= 500
 *      -10^4 <= lists[i][j] <= 10^4
 *      lists[i] 按 升序 排列
 *      lists[i].length 的总和不超过 10^4
 *
 * @author ywh
 * @since 11/11/2019
 */
public class LeetCode23 {

    /**
     * 逐个归并链表
     *
     * Time: O(k*n), Space: O(1)
     *
     * @param lists
     * @return
     */
    public ListNode mergeKSortedListsOneByOne(ListNode[] lists) {
        ListNode ret = null;
        if (lists == null || lists.length == 0) {
            return null;
        }
        for (ListNode list : lists) {
            ListNode dummy = new ListNode(), p = dummy;
            for (; ret != null && list != null; p = p.next) {
                if (ret.val < list.val) {
                    p.next = ret;
                    ret = ret.next;
                } else {
                    p.next = list;
                    list = list.next;
                }
            }
            if (ret != null) {
                p.next = ret;
            }
            if (list != null) {
                p.next = list;
            }
            ret = dummy.next;
        }
        return ret;
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
        ListNode l = merge(lists, start, mid), r = merge(lists, mid + 1, end);
        ListNode dummy = new ListNode(), p = dummy;
        for (;l != null && r != null; p = p.next) {
            if (l.val < r.val) {
                p.next = l;
                l = l.next;
            } else {
                p.next = r;
                r = r.next;
            }
        }
        p.next = l == null? r: l;
        return dummy.next;
    }

    /**
     * 分治解法
     *
     * Time: O(k*n*log(k)), Space: O(log(k))
     *
     * @param lists
     * @return
     */
    public ListNode mergeKSortedListsDivideConquer(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    /**
     * 最小堆解法：
     * 根据头节点判断，把链表列表添加到最小堆，堆顶元素即为头节点最小的链表。
     * 每次取堆顶元素，截取头部加入结果链表；如果该元素不是链表结尾（.next = null），则把后续节点重新入堆；
     * 循环直到堆空即可。
     *
     * Time: O(k*n*log(k)), Space: O(k)
     *
     * @param lists
     * @return
     */
    public ListNode mergeKSortedListsMinHeap(ListNode[] lists) {

        // 最小堆（优先级队列）：每次从中获取最小元素，在这里表示头节点值最小的链表。
        Queue<ListNode> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode list : lists) {
            if (list != null) {
                q.add(list);
            }
        }
        ListNode dummy = new ListNode(), p = dummy, min;
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
}
