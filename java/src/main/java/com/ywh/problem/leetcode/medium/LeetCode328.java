package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

/**
 * 奇偶链表
 * [链表]
 *
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
 * 请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * 示例 1:
 *      输入: 1->2->3->4->5->NULL
 *      输出: 1->3->5->2->4->NULL
 * 示例 2:
 *      输入: 2->1->3->5->6->4->7->NULL
 *      输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 *      应当保持奇数节点和偶数节点的相对顺序。
 *      链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 *
 * @author ywh
 * @since 09/12/2019
 */
public class LeetCode328 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode head2 = head.next, even = head2, odd = head;
        while (even != null && even.next != null) {
            //         +-------------+
            //         |             |
            //  +------+------+      |
            //  |      |      ↓      ↓
            // [1]   [2]    [3]    [4]
            //               odd    even
            //
            // 先修改后继指针，再修改自身，不断交错执行
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        // 奇链表的末尾指向偶链表的开头
        odd.next = head2;
        return head;
    }
}
