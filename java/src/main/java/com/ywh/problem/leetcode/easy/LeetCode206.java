package com.ywh.problem.leetcode.easy;

import com.ywh.ds.list.ListNode;

/**
 * 反转链表
 * [链表]
 *
 * 反转一个单链表。
 * 示例：
 *      输入: 1->2->3->4->5->NULL
 *      输出: 5->4->3->2->1->NULL
 * 进阶：
 *      你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
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
    public ListNode reverseListIterative(ListNode head) {
        // 三个指针，一个指向当前节点，一个指向上一个节点，一个指向下一个节点。
        ListNode pre = null, next;

        //       2
        //   +--------+
        //   ↓        |        1
        // [pre] -> [cur] -> [next]     =>      [  ] <- [pre] -> [cur] -> [next]
        //            3        4
        //          [pre]    [cur]
        while (head != null) {
            // 先保存当前节点的下一个节点。
            next = head.next;
            // 把当前节点 next 域指向上一个节点。
            head.next = pre;
            // 把上一个节点替换为当前节点、把当前节点替换为最初保存的 next 节点。
            pre = head;
            head = next;
        }
        // 最终：
        //         return
        // [  ] <- [pre]   null(cur)
        //
        return pre;
    }

    /**
     * 递归解法
     * Time: O(n), Space: O(n)
     *
     * @param head
     * @return
     */
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ret = reverseListRecursive(head.next);
        //          1
        //    +----------+
        //    ↓          |
        //  [head]  x  [next]
        //          2
        head.next.next = head;
        head.next = null;
        return ret;
    }
}
