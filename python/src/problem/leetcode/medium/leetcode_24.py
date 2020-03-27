# coding=utf-8

from src.data_structure.data_structure import ListNode


class Solution:
    """
    两两交换链表中的节点
    """

    def swap_pairs1(self, head: ListNode) -> ListNode:
        """
        Time: O(n), Space: O(n)

        :param head:
        :return:
        """
        if not head or not head.next:
            return head
        next = head.next
        head.next = self.swap_pairs1(next.next)
        next.next = head
        return next

    def swap_pairs2(self, head: ListNode) -> ListNode:
        """
        Time: O(n), Space: O(1)

        :param head:
        :return:
        """
        pre = dummy = ListNode(0, head)
        while pre.next and pre.next.next:
            first, second = pre.next, pre.next.next
            pre.next = second
            first.next = second.next
            second.next = first
            pre = first
        return dummy.next
