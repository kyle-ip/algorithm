package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

/**
 * 旋转链表
 * [链表] [双指针]
 * 
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 示例 1：
 *      输入：head = [1,2,3,4,5], k = 2
 *      输出：[4,5,1,2,3]
 * 示例 2：
 *      输入：head = [0,1,2], k = 4
 *      输出：[2,0,1]
 * 提示：
 *      链表中节点的数目在范围 [0, 500] 内
 *      -100 <= Node.val <= 100
 *      0 <= k <= 2 * 10^9
 *
 * @author ywh
 * @since 2019/2/21
 */
public class LeetCode61 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRightToLeft(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }

        // 计算链表长度、把链表尾首相连成环：
        // head                        end
        // [1] -> [2] -> [3] -> [4] -> [5]
        //  |                           |
        //  +---------------------------+
        ListNode end = head;
        int n = 0;
        for (; end.next != null; end = end.next, n++);
        end.next = head;

        // 新建尾指针，定位到链表的倒数第 k 位，则新链表头为新尾指针的下一个节点：
        //
        //             newEnd newHead
        // [1] -> [2] -> [3] -> [4] -> [5]
        //  |                           |
        //  +---------------------------+
        k %= n + 1;
        ListNode newEnd = head;
        for (int i = 0; i < n - k; i++, newEnd = newEnd.next);
        ListNode newHead = newEnd.next;

        // 最后把链表的尾指针置空，解环：
        //
        //             newEnd newHead
        // [1] -> [2] -> [3]    [4] -> [5]
        //  |                           |
        //  +---------------------------+
        //
        // [4] -> [5] -> [1] -> [2] -> [3]
        newEnd.next = null;
        return newHead;
    }
}
