# coding=utf-8
from typing import List

from src.data_structure.data_structure import ListNode


class Solution:
    """
    移除链表元素
    """

    def remove_elements(self, head: ListNode, val: int) -> ListNode:
        """
        Time: O(n), Space: O(1)
        :param head:
        :param val:
        :return:
        """

        cur = dummy = ListNode(-1, head)
        while cur.next:
            if cur.next.val == val:
                cur.next = cur.next.next
            else:
                cur = cur.next
        return dummy.next

