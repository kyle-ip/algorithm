package com.ywh.algorithm.leetcode.medium;

import com.ywh.model.ListNode;

/**
 * 有序链表删除重复节点
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode82 {

    /**
     * 1 1 2 4
     * 1 2 2 4
     * 1 2 4 4
     * 1 2 3 4
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicatesInSortedList(ListNode head) {

        ListNode dummy = new ListNode(-1, head);
        ListNode left = dummy, right = left.next;

        while (right.next != null) {

            // 右指针不断向右移动，直到与其下一个节点的值不同
            while (right.next != null && right.val == right.next.val) {
                right = right.next;
            }

            // 左指针的 next 域不是右指针，表示相隔至少1个节点且两者之间都是重复元素，需要删除
            if (left.next != right) {
                left.next = right.next;
            }
            // 左指针的 next 域是右指针，则向前移动一位即可
            else {
                left = right;
            }

            // 每次操作后都把右指针重置为左指针的后一位
            right = left.next;
        }

        return dummy.next;
    }

}
