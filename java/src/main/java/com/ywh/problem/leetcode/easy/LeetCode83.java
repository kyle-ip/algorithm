package com.ywh.problem.leetcode.easy;

import com.ywh.ds.list.ListNode;
import com.ywh.problem.leetcode.medium.LeetCode82;

/**
 * 删除排序链表中的重复元素
 * [链表]
 * 
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 示例 1:
 *      输入: 1->1->2
 *      输出: 1->2
 * 示例 2:
 *      输入: 1->1->2->3->3
 *      输出: 1->2->3
 * 提示：
 *      链表中节点数目在范围 [0, 300] 内
 *      -100 <= Node.val <= 100
 *      题目数据保证链表已经按升序排列
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode83 {

    /**
     * 区别于 {@link LeetCode82}，出现重复的值只保留一个。
     *
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicatesInSortedList(ListNode head) {
        if (head == null) {
            return head;
        }
        for (ListNode cur = head; cur.next != null; ) {

            // 删掉 cur.next：
            //  +-------------+
            //  |             |
            // cur            ↓
            // [1] -> [1] -> [2] -> [3] -> null
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            }

            //        cur => cur
            // [1] -> [1] -> [2] -> [3] -> null
            else {
                cur = cur.next;
            }
        }
        return head;
    }
}
