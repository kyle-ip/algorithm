package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表 II
 * [链表] [双指针]
 * 
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * 说明：不允许修改给定的链表。
 * 进阶：
 *      你是否可以使用 O(1) 空间解决此题？
 * 示例 1：
 *      输入：head = [3,2,0,-4], pos = 1
 *      输出：返回索引为 1 的链表节点
 *      解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 *      输入：head = [1,2], pos = 0
 *      输出：返回索引为 0 的链表节点
 *      解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 *      输入：head = [1], pos = -1
 *      输出：返回 null
 *      解释：链表中没有环。
 * 提示：
 *      链表中节点的数目范围在范围 [0, 10^4] 内
 *      -10^5 <= Node.val <= 10^5
 *      pos 的值为 -1 或者链表中的一个有效索引
 * 
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode142 {

    /**
     * 使用集合缓存节点，通过判断重复找到环的起点
     * Time: O(n), Space: O(n)
     *
     * @param head
     * @return
     */
    public ListNode startNodeOfCycleHashSet(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        for (ListNode p = head; p != null; p = p.next) {
            if (set.contains(p)) {
                return p;
            }
            set.add(p);
        }
        return null;
    }

    /**
     * 使用快慢指针的方法判断是否成环，当快慢指针相遇表示确定成环，
     * 另建新指针从链表头部出发，与慢指针同步向前移动，
     * 新指针与慢指针相遇的位置即为环的起点
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public ListNode startNodeOfCycleTwoPointer(ListNode head) {
        for (ListNode fast = head, slow = head; fast != null && fast.next != null; ) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {

                for (ListNode cur = head; cur != slow; ) {
                    cur = cur.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

}
