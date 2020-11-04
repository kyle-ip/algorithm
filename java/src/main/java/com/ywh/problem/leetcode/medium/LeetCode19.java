package com.ywh.problem.leetcode.medium;

import com.ywh.model.ListNode;

/**
 * 移除单链表倒数第 n 个节点
 * [链表] [双指针]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode19 {

    /**
     * 由于倒数第 n 位可能恰好是链表开头，因此引入辅助节点作为表头；
     * 快指针先向前走 n 步，如此时未走到结尾，表示链表长度 > n；
     * 此时慢指针从头部出发，快慢指针同步向前走，直到快指针走到结尾；
     * 由于快慢指针相隔 n 位，当快指针走到结尾，慢指针正好到达倒数第 n 位，执行删除操作即可；
     * 注意最后返回的是辅助节点的下一位
     *
     * Time: O(k), Space: O(1)
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head), fast = dummy, slow = dummy;
        for (; fast != null && n > 0; n--, fast = fast.next);

        // n 还没减完，fast 已到末尾，表示链表长度不够，返回第一个节点
        if (n > 0) {
            return dummy.next;
        }

        // fast、slow 相隔 n 个节点，当 fast 定位最后一个节点，slow 定位到倒数第 n 个节点的前一个节点。
        // when n == 4：
        // step0:    slow/fast
        //            dummy -> [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> null

        // n           4       3      2      1      0
        // step1:     slow                         fast
        //           dummy -> [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> [ ] -> null

        // step2:                    slow                        fast
        //           dummy -> [ ] -> [ ] -> [x] -> [ ] -> [ ] -> [ ] -> null
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // step3:                    slow                        fast
        //           dummy -> [ ] -> [ ] -> [x] -> [ ] -> [ ] -> [ ] -> null
        //                            |             ↑
        //                            +-------------+
        slow.next = slow.next.next;
        return dummy.next;
    }

}
