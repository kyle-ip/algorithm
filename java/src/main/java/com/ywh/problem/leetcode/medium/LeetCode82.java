package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

/**
 * 有序链表删除重复节点
 * [链表]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode82 {

    /**
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
        ListNode dummy = new ListNode(-1, head), left = dummy, right = dummy.next;
        while (right != null) {

            // 右指针不断向右移动，直到与其下一个节点的值不同（跳过重复值）。
            while (right.next != null && right.val == right.next.val) {
                right = right.next;
            }
            // 左指针的 next 域不是右指针，表示（相隔至少 1 个节点）此时 [left, right] 都是重复值，保留一个 left 即可。
            //  +--------------------+
            //  |                    ↓
            // [1] -> [1] -> [1] -> [2] -> ...
            // left          right
            if (left.next != right) {
                left.next = right.next;
            }
            // 左指针的 next 域是右指针，则向前移动一位。
            // [1] -> [2] -> [3] -> [3] -> ...
            //      left/right
            else {
                left = right;
            }
            // 每轮循环结束后都让 right 领先 left 一位。
            right = left.next;
        }
        return dummy.next;
    }

}
