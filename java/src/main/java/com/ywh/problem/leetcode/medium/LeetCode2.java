package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

/**
 * 求两个单链表之和
 * [链表] [数学]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode2 {

    /**
     * 使用两个变量分别记录进位与和，同步移动两个链表的指针、取元素求和；
     * 只要循环中至少一个指针、进位不为空，则以它们的和为值生成新节点追加到新链表。
     * Time: O(n), Space: O(1)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoLinkedListNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), p = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode(sum % 10);
            p = p.next;
            carry = sum / 10;
        }
        return dummy.next;
    }
}
