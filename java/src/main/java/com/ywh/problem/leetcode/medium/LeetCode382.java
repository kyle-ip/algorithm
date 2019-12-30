package com.ywh.problem.leetcode.medium;

import com.ywh.model.ListNode;

/**
 * 链表随机节点
 * [链表] [蓄水池抽样]
 *
 * @author ywh
 * @since 30/12/2019
 */
public class LeetCode382 {

    private int count = 0;

    private ListNode head;

    public LeetCode382(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            count++;
        }
        this.head = head;
    }

    /**
     * Time: O(n), Space: O(1)
     * 蓄水池抽样算法：https://leetcode-cn.com/problems/linked-list-random-node/solution/
     *
     * @return
     */
    public int getRandom() {
        ListNode cur = head;
        int ran = (int) (Math.random() * count);
        while (ran > 0) {
            cur = cur.next;
            ran--;
        }
        return cur.val;
    }

}
