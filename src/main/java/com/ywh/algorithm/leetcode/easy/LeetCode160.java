package com.ywh.algorithm.leetcode.easy;

import com.ywh.model.ListNode;

/**
 * 链表的相交节点（注意：链表相交后必重合）
 * [链表]
 *
 * @author ywh
 * @since 2/15/2019
 */
public class LeetCode160 {

    /**
     * 循环对比两个链表：
     * 两个指针分别从两个链表头部开始出发；
     * 当到达终点仍未相遇、且两个指针不同时为空，则为空的指针从对方链表的开头出发，直到两个指针相遇即可返回交点；
     * 如没有交点，则最终两个指针相等且为空
     * Time: O(m+n), Space: O(1)
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeWithoutLen(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null? headB: pA.next;
            pB = pB == null? headA: pB.next;
        }

        return pA;
    }
    /**
     * 分别遍历两个链表，求出两个链表的长度，并求出差值 delta；
     * 两个指针分别从两个链表头部开始出发，并先让长链表的指针先走 delta 步；
     * 如两个链表有交点，则当两个指针相等时所指的就是交点，返回即可；
     * 如两个链表没有交点，则两个指针都走到尽头，返回 null
     * Time: O(m+n), Space: O(1)
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeWithLen(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lenA = 0, lenB = 0, delta;
        for (ListNode pA = headA; pA != null; pA = pA.next, lenA++);
        for (ListNode pB = headB; pB != null; pB = pB.next, lenB++);

        ListNode pA = headA, pB = headB;
        delta = lenA > lenB? lenA - lenB: lenB - lenA;

        for (int i = 0; lenA > lenB && i < delta; i++, pA = pA.next);
        for (int i = 0; lenB > lenA && i < delta; i++, pB = pB.next);

        for (; pA != null && pB != null && pA != pB; pA = pA.next, pB = pB.next);

        if (pA == null || pB == null) {
            return null;
        }
        return pA;
    }

}
