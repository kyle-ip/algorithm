package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

/**
 * 两数相加
 * [链表] [数学]
 *
 * 给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0开头。
 *
 * 示例：
 *      输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *      输出：7 -> 0 -> 8
 *      原因：342 + 465 = 807
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
