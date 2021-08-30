package com.ywh.problem.leetcode.easy;

import com.ywh.ds.list.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 回文链表
 * [链表] [双指针]
 *
 * 请判断一个链表是否为回文链表。
 * 示例 1:
 *      输入: 1->2
 *      输出: false
 * 示例 2:
 *      输入: 1->2->2->1
 *      输出: true
 * 进阶：
 *      你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * @author ywh
 * @since 2/14/2019
 */
public class LeetCode234 {

    /**
     * 两次遍历：
     * 第一次遍历使用辅助栈缓存元素；
     * 第二次遍历时对比指针所指的值和从栈弹出的值（倒序输出）。
     * Time: O(n), Space: O(n)
     *
     * @param head
     * @return
     */
    public boolean isPalindromeUsingStack(ListNode head) {
        ListNode p = head;
        Deque<Integer> stack = new LinkedList<>();
        for (; p != null; p = p.next) {
            stack.push(p.val);
        }
        for (p = head; !stack.isEmpty(); p = p.next) {
            if (stack.pop() != p.val) {
                return false;
            }
        }
        return true;
    }


    /**
     * 遍历一次计算出链表长度，把前一半链表反转，得到两个链表；
     * 同步遍历两个链表、对比每一位
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public boolean isPalindromeReverseList(ListNode head) {
        int len = 0;
        for (ListNode p = head; p != null; p = p.next, len++);

        ListNode prev = null, cur = head;
        for (int i = 0; i < len / 2; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        // before:
        // [1] -> [2] -> [3] -> [2] -> [1] -> [null]         [1] -> [2] -> [3] -> [3] -> [2] -> [1] -> [null]

        // after:
        //        pre    cur                                               pre    cur
        // [1] <- [2] -> [3] -> [2] -> [1] -> [null]         [1] <- [2] <- [3] -> [3] -> [2] -> [1] -> [null]

        // 如果总长度是奇数，则跳过中间节点。从这一步起，pre 和 cur 分别指向断开的两个链表的头部：
        //        pre           cur                                        pre    cur
        // [1] <- [2] -> [3] -> [2] -> [1] -> [null]         [1] <- [2] <- [3] -> [3] -> [2] -> [1] -> [null]
        for (cur = len % 2 == 0? cur: cur.next; prev != null && cur != null; prev = prev.next, cur = cur.next) {
            if (prev.val != cur.val) {
                return false;
            }
        }
        return true;
    }
}
