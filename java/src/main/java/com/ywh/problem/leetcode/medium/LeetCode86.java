package com.ywh.problem.leetcode.medium;


import com.ywh.ds.list.ListNode;

/**
 * 分隔链表
 * [链表] [双指针]
 *
 * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 示例：
 *      输入：head = 1->4->3->2->5->2, x = 3
 *      输出：1->2->2->4->3->5
 *
 * @author ywh
 * @since 2019/2/21
 */
public class LeetCode86 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partitionAndPreserveOrder(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        // 两个辅助链表，分别按顺序存放较小的元素和较大的元素（注意头部为辅助节点，不包含在最终结果中）。
        ListNode s = new ListNode(0), g = new ListNode(0), ps = s, pg = g;
        for (ListNode p = head; p != null; p = p.next) {
            if (p.val < x) {
                ps.next = p;
                ps = ps.next;
            } else {
                pg.next = p;
                pg = pg.next;
            }
        }

        // 把小链表指向大链表的开头，大链表指针置空。
        ps.next = g.next;
        pg.next = null;
        return s.next;
    }

}
