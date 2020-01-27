# coding=utf-8
from typing import List

from src.data_structure.data_structure import ListNode


class Solution:
    """
    删除链表中的节点
    """

    def delete_node(self, node: ListNode) -> None:
        """
        Time: O(1), Space: O(1)
        :param node:
        :return:
        """
        node.val = node.next.val
        node.next = node.next.next

