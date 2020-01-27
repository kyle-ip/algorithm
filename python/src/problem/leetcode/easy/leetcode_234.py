# coding=utf-8
from typing import List

from src.data_structure.data_structure import ListNode


class Solution:
    """
    判断单链表是否为回文链表
    """

    def is_palindrome(self, head: ListNode) -> bool:
        """
        Time: O(n), Space: O(n)
        :param head:
        :return:
        """
        p = head
        stack = []
        while p:
            stack.append(p.val)
            p = p.next

        p = head
        while len(stack) > 0:
            if stack.pop() != p.val:
                return False
            p = p.next
        return True