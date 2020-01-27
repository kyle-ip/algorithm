# coding=utf-8
from typing import List

from src.data_structure.data_structure import ListNode


class Solution:
    """
    反转链表
    """

    def reverse_list(self, head: ListNode) -> ListNode:
        """

        :param head:
        :return:
        """
        pre, cur = None, head
        while cur:
            next = cur.next
            cur.next = pre
            pre = cur
            cur = next
        return pre

