package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;
import com.ywh.problem.leetcode.easy.LeetCode83;

/**
 * 删除排序链表中的重复元素 II
 * [链表]
 *
 * 示例 1:
 *      输入: 1->2->3->3->4->4->5
 *      输出: 1->2->5
 * 示例 2:
 *      输入: 1->1->1->2->3
 *      输出: 2->3
 * 提示：
 *      链表中节点数目在范围 [0, 300] 内
 *      -100 <= Node.val <= 100
 *      题目数据保证链表已经按升序排列
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode82 {

    /**
     * 区别于 {@link LeetCode83}，出现重复的值全部都不要。
     *
     * 需要兼容以下情况：
     * 1 1 2 4
     * 1 2 2 4
     * 1 2 4 4
     * 1 2 3 4
     *
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicatesInSortedList(ListNode head) {
        ListNode dummy = new ListNode(-1, head);

        // l 指向最近一个非重复元素。
        for (ListNode l = dummy, r = head; r != null; r = l.next) {

            // 右指针不断向右移动，直到与其下一个节点的值不同（跳过重复值）。
            for (; r.next != null && r.val == r.next.val; r = r.next);

            // 左指针的 next 域不是右指针，表示（相隔至少 1 个节点）此时 [left.next, right] 是重复值，全部都不要。
            //  +--------------------+
            //  |                    ↓
            // [0] -> [1] -> [1] -> [2] -> ...
            // left          right
            if (l.next != r) {
                l.next = r.next;
            }
            // 左指针的 next 域是右指针，则移动一位。
            // [1] -> [2] -> [3] -> [3] -> ...
            //      left/right
            else {
                l = r;
            }
        }
        return dummy.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head), cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
