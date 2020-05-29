# coding=utf-8
from typing import List

from src.data_structure.data_structure import ListNode


class Solution:
    """
    旋转单链表
    """

    def rotate_right(self, head: ListNode, k: int) -> ListNode:
        """
        Time: O(n), Space: O(1)
        :param head:
        :param k:
        :return:
        """
        if not head or not head.next or k <= 0:
            return head

        end = head
        n = 1
        while end.next:
            end = end.next
            n += 1
        end.next = head
        k %= n
        new_end = head
        for i in range(n - k - 1):
            new_end = new_end.next
        new_head = new_end.next

        new_end.next = None
        return new_head
