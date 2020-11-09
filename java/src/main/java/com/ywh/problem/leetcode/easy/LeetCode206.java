package com.ywh.problem.leetcode.easy;

import com.ywh.model.ListNode;

/**
 * 反转链表
 * [链表]
 *
 * @author ywh
 * @since 2/14/2019
 */
public class LeetCode206 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        // 三个指针，一个指向当前节点，一个指向上一个节点，一个指向下一个节点。
        ListNode pre = null, cur = head, next;

        //       2
        //   +--------+
        //   ↓        |        1
        // [pre] -> [cur] -> [next]     =>      [  ] <- [pre] -> [cur] -> [next]
        //            3        4
        //          [pre]    [cur]
        while (cur != null) {
            // 先保存当前节点的下一个节点。
            next = cur.next;
            // 把当前节点 next 域指向上一个节点。
            cur.next = pre;
            // 把上一个节点替换为当前节点、把当前节点替换为最初保存的 next 节点。
            pre = cur;
            cur = next;
        }
        // 最终：
        //         return
        // [  ] <- [pre]   null(cur)
        //
        return pre;
    }
}
