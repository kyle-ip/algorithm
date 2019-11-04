package com.ywh.problem.leetcode.easy;

import com.ywh.model.ListNode;

/**
 * 反转单链表
 * [链表]
 *
 * @author ywh
 * @since 2/14/2019
 */
public class LeetCode206 {

    /**
     * 三个指针，一个指向当前节点，一个指向上一个节点，一个指向下一个节点；
     * 执行循环：
     * 先保存当前节点的下一个节点；
     * 把当前节点 next 域指向上一个节点；
     * 把上一个节点替换为当前节点；
     * 把当前节点替换为最初保存的 next 节点。
     *
     * 注意最终返回的是上一个节点。
     *
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
