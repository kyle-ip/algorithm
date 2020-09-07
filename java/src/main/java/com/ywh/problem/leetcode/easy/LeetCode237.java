package com.ywh.problem.leetcode.easy;

import com.ywh.model.ListNode;

/**
 * 删除链表节点
 * [链表]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode237 {

    /**
     * 把下一个节点的值复制过来、并把 next 域指向其下一个节点
     *
     * Time: O(1), Space: O(1)
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
