package com.ywh.problem.leetcode.easy;

import com.ywh.ds.list.ListNode;

/**
 * 合并两个有序链表
 * [链表]
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 示例：
 *      输入：1->2->4, 1->3->4
 *      输出：1->1->2->3->4->4
 *
 * @author ywh
 * @since 2/15/2019
 */
public class LeetCode21 {

    /**
     * Time: O(m+n), Space: O(1)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        for (ListNode dummy = new ListNode(0), cur = dummy; ; cur = cur.next) {
            if (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    cur.next = l1;
                    l1 = l1.next;
                } else {
                    cur.next = l2;
                    l2 = l2.next;
                }
            } else {
                cur.next = l1 != null ? l1 : l2;
                return dummy.next;
            }
        }
    }
}
