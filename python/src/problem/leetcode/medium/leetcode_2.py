# coding=utf-8

from src.data_structure.data_structure import ListNode

class Solution:

    def add_two_numbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        """
        Time: O(n), Space: O(1)
        :param l1:
        :param l2:
        :return:
        """
        p = dummy = ListNode(-1)
        carry = 0
        while l1 or l2 or carry > 0:
            sum = carry
            if l1:
                sum += l1.val
                l1 = l1.next
            if l2:
                sum += l2.val
                l2 = l2.next
            p.next = ListNode(sum % 10)
            p = p.next
            carry = sum // 10
        return dummy.next

