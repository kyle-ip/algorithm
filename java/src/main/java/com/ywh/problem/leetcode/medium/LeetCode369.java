package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;
import com.ywh.problem.leetcode.easy.LeetCode66;

/**
 * 链表加一
 * 类似 {@link LeetCode66}，区别在于单链表不能从后往前数
 *
 * [链表]
 *
 * @author ywh
 * @since 26/12/2019
 */
public class LeetCode369 {

    /**
     * Time: O(n), pace: O(1)
     *
     * @param head
     * @return
     */
    public ListNode plusOne(ListNode head) {

        // 先创建一个节点，初始值设为 1，置于 head 之前（用作可能表示的进位）
        // 再创建一个指针从该节点开始，寻找最右边的、不为 9 的数字
        ListNode maybe = new ListNode(0, head), notNine = maybe;
        // 找到最右边不为 9 的位置，+1
        for (ListNode p = head; p != null; p = p.next) {
            if (p.val != 9) {
                notNine = p;
            }
        }

        // 该数字后面全部都是 9，加一后将其后面所有的 9 都设为 0，如 090951[1]99 => 09095[2]00（最终忽略补位，返回 [9]095200）
        notNine.val += 1;
        for (ListNode p = notNine.next; p != null; p = p.next) {
            p.val = 0;
        }

        // 如果最右边的、不为 9 的数字正是开头的补位，则直接返回该补位，如 09999 => 10000 返回 [1]0000
        return notNine == maybe? maybe: head;
    }
}
