package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

/**
 * 两两交换链表中的节点
 * [链表]
 * 
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1：
 *      输入：head = [1,2,3,4]
 *      输出：[2,1,4,3]
 * 示例 2：
 *      输入：head = []
 *      输出：[]
 * 示例 3：
 *      输入：head = [1]
 *      输出：[1]
 * 提示：
 *      链表中节点的数目在范围 [0, 100] 内
 *      0 <= Node.val <= 100
 * 进阶：
 *      你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
 *
 * @author ywh
 * @since 27/03/2020
 */
public class LeetCode24 {

    /**
     * 递归解法，结束条件为最后不足两个节点。
     *
     * Time: O(n), Space: O(n)
     *
     * @param head
     * @return
     */
    public ListNode swapPairsRecursive(ListNode head) {
        // [1] -> [2] -> [3] -> [4] -> [5] -> [6] -> null

        // 最底层的递归栈帧：head == [5]
        // next = head.next => [5].next => [6]
        // head.next => [5].next = f([6].next) => f(null) => null
        // next.next = [6].next => [5]
        //                                      [1] -> [2] -> [3] -> [4] -> [6] -> [5] -> null
        // return: [6] ---------------------------------------------+
        //                                                          |
        // 其上一层的栈帧：head == [4]                                |
        // next = head.next => [4].next => [5]                      |
        // head.next => [4].next = f([5].next) => f([6]]) <---------+
        // next.next => [5].next => [4]
        // return: [5]
        // ...

        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairsRecursive(next.next);
        next.next = head;
        return next;
    }

    /**
     * 以 pre 为辅助，交换其下一个（first）和下下一个（second），每轮跳两个
     *
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode swapPairsIterative(ListNode head) {
        ListNode dummy = new ListNode(0, head), prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            //      三步交换：                         循环最后 prev 定位到 first 的位置：
            //                     2
            //           1  +-------------+
            //       +------|------+      |      =>
            //       |      |   3  ↓      ↓
            //      [ ]    [ ] <- [ ]    [ ] ->       [ ] -> [ ] -> [ ] -> [ ]
            //      prev  first  second                    second first/prev
            ListNode first = prev.next, second = prev.next.next;
            prev.next = second;
            first.next = second.next;
            second.next = first;
            prev = first;
        }
        return dummy.next;
    }
}
