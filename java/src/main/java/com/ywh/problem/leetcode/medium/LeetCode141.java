package com.ywh.problem.leetcode.medium;

import com.ywh.model.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断单链表是否有环
 * [链表] [双指针]
 *
 * @author ywh
 * @since 2/14/2019
 */
public class LeetCode141 {

    /**
     * 使用快慢两个指针，快指针每次移动两位、慢指针每次移动一位；
     * 如果链表成环，则快指针走完两圈时慢指针正好走完一圈、两指针在起点相遇；
     * 如果链表不成环，则快指针必然率先到达终点
     *
     * @param head
     * @return
     */
    public boolean hasCycleWithTwoPointer(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 使用 Hash 实现
     *
     * @param head
     * @return
     */
    public boolean hasCycleWithHashSet(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        for(ListNode p = head; p != null; p = p.next) {
            if (set.contains(p)) {
                return true;
            }
            set.add(p);
        }
        return false;
    }
}
