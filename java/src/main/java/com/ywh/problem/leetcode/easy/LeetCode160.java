package com.ywh.problem.leetcode.easy;

import com.ywh.ds.list.ListNode;

/**
 * 相交链表
 * [链表]
 *
 * 编写一个程序，找到两个单链表相交的起始节点。
 * 如下面的两个链表：
 *              [a1] -> [a2] --+
 *                             |-> [c1] -> [c2] -> [c3]
 *      [b1] -> [b2] -> [b3] --+
 * 在节点 c1 开始相交。
 * 示例 1：
 *      输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 *      输出：Reference of the node with value = 8
 *      输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 *      从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 *      在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * 示例 2：
 *      输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 *      输出：null
 *      输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 *      由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 *      解释：这两个链表不相交，因此返回 null。
 * 注意：
 *      如果两个链表没有交点，返回 null.
 *      在返回结果后，两个链表仍须保持原有的结构。
 *      可假定整个链表结构中没有循环。
 *      程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 *
 * @author ywh
 * @since 2/15/2019
 */
public class LeetCode160 {

    /**
     * 循环对比两个链表：
     * 两个指针分别从两个链表头部开始出发，
     * 当到达终点仍未相遇、且两个指针不同时为空，则为空的指针从对方链表的开头出发，直到两个指针相遇即可返回交点；
     * 如没有交点，则最终两个指针相等且为空
     * Time: O(m+n), Space: O(1)
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeWithoutLen(ListNode headA, ListNode headB) {
        // 没有交点：
        // A: [2] -> [6] -> [4]
        // B: [1] -> [5]
        // pA: 2, 6, 4, n, 1, 5, n (break)
        // pB: 1, 5, n, 2, 6, 4, n

        // 有交点：
        // A: [0] -> [1] -> [2] -> [4] -> null
        //                   ↑
        // B:               [3]
        // pA: 0, 1, 2, 4, n, 3, 2 (break)
        // pB: 3, 2, 4, n, 0, 1, 2

        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA != null? pA.next: headB;
            pB = pB != null? pB.next: headA;
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
        delta = Math.abs(lenA - lenB);
        for (int i = 0; lenA > lenB && i < delta; i++, pA = pA.next);
        for (int i = 0; lenB > lenA && i < delta; i++, pB = pB.next);
        for (; pA != null && pB != null && pA != pB; pA = pA.next, pB = pB.next);
        return pA;
    }

}
