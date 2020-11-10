package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 单链表中圆环的开始节点
 * [链表] [双指针]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode142 {

    /**
     * 使用集合缓存节点，通过判断重复找到环的起点
     * Time: O(n), Space: O(n)
     *
     * @param head
     * @return
     */
    public ListNode startNodeOfCycleHashSet(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        for (ListNode p = head; p != null; p = p.next) {
            if (set.contains(p)) {
                return p;
            }
            set.add(p);
        }
        return null;
    }

    /**
     * 使用快慢指针的方法判断是否成环，当快慢指针相遇表示确定成环，
     * 另建新指针从链表头部出发，与慢指针同步向前移动，
     * 新指针与慢指针相遇的位置即为环的起点
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode startNodeOfCycleTwoPointer(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode cur = head;
                while (cur != slow) {
                    cur = cur.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

}
