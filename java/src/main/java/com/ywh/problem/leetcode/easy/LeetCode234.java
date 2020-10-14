package com.ywh.problem.leetcode.easy;

import com.ywh.model.ListNode;

import java.util.Stack;

/**
 * 判断单链表是否为回文链表
 * [链表] [双指针]
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
        Stack<Integer> stack = new Stack<>();
        while (p != null) {
            stack.push(p.val);
            p = p.next;
        }
        p = head;
        while (!stack.isEmpty()) {
            if (stack.pop() != p.val) {
                return false;
            }
            p = p.next;
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
        for (ListNode p = head; p != null; p = p.next, ++len);

        ListNode pre = null, cur = head, next;
        for (int i = 0; i < len / 2; i ++) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
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
        if (len % 2 == 1) {
            cur = cur.next;
        }

        for (; pre != null && cur != null; pre = pre.next, cur = cur.next) {
            if (pre.val != cur.val) {
                return false;
            }
        }
        return true;
    }
}
