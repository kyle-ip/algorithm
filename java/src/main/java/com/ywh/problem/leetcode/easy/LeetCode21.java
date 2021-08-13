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
        ListNode dummy = new ListNode(), p = dummy;
        for (; l1 != null && l2 != null; p = p.next) {
            if (l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
        }
        p.next = l1 == null? l2: l1;
        return dummy.next;
    }
}
