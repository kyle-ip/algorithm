package com.ywh.problem.leetcode.easy;

import com.ywh.ds.list.ListNode;

/**
 * 单链表删除数字
 * [链表]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode203 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode remove(ListNode head, int val) {

        // 注意链表第一个元素也可能是 val，所以需要在头部前创建一个辅助节点 dummy。
        ListNode dummy = new ListNode(-1, head), cur = dummy;

        // 删除一个节点需要将其前一个节点的 next 域指向其后一个节点，所以需要对目标节点的前一个节点操作
        while (cur.next != null) {
            // [cur] -> [*] -> [ ] -> null
            //   |              ↑
            //   +------------- +
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        // 不需要单独对最后一个节点进行判断，返回辅助节点的下一个节点。
        return  dummy.next;
    }
}
