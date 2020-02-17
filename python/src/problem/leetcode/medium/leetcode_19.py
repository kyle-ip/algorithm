# coding=utf-8
from typing import List
import sys

from src.data_structure.data_structure import ListNode


class Solution:
    """
    移除单链表倒数第 n 个节点
    """

    def remove_nth_from_end(self, head: ListNode, n: int) -> ListNode:
        """
        Time: O(k), Space: O(1)
        :param head:
        :param n:
        :return:
        """
        slow = fast = dummy = ListNode(-1, head)
        while fast and n > 0:
            n -= 1
            fast = fast.next

        if n > 0:
            return dummy.next

        while fast and fast.next:
            fast = fast.next
            slow = slow.next

        slow.next = slow.next.next

        return dummy.next
