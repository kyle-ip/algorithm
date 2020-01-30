# coding=utf-8
from src.data_structure.data_structure import TreeNode, ListNode


class Solution:
    """
    链表的中间结点
    """

    def middle_node(self, head: ListNode) -> ListNode:
        """
        Time: O(n), Space: O(1)
        :param head:
        :return:
        """
        slow = fast = head
        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next
        return slow

