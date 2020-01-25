# coding=utf-8
from typing import List

from src.data_structure.data_structure import ListNode


class Solution:

    @staticmethod
    def merge_two_lists(l1: ListNode, l2: ListNode) -> ListNode:
        """
        Time: O(n), Space: O(1)
        :param l1:
        :param l2:
        :return:
        """

        p = dummy = ListNode(-1)
        while l1 and l2:
            if l1.val < l2.val:
                p.next = l1
                l1 = l1.next
            else:
                p.next = l2
                l2 = l2.next
            p = p.next
        if l1:
            p.next = l1
        if l2:
            p.next = l2
        return dummy.next
