package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 两数相加 II
 * [链表]
 *
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 进阶：
 *      如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * 示例：
 *      输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 *      输出：7 -> 8 -> 0 -> 7
 *
 * @author ywh
 * @since 27/02/2021
 */
public class LeetCode445 {

    /**
     * Time: O(max(m,n)), Space: O(m+n)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<>(), stack2 = new LinkedList<>();
        for (; l1 != null; l1 = l1.next) {
            stack1.push(l1.val);
        }
        for (; l2 != null; l2 = l2.next) {
            stack2.push(l2.val);
        }
        int carry = 0, sum;
        ListNode p = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            sum = carry;
            sum += stack1.isEmpty()? 0: stack1.pop();
            sum += stack2.isEmpty()? 0: stack2.pop();
            ListNode node = new ListNode(sum % 10);
            node.next = p;
            p = node;
            carry = sum / 10;
        }
        return p;
    }
}
