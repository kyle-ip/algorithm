# coding=utf-8
from src.data_structure.data_structure import ListNode


class Solution:
    """
    有序链表去重
    """

    def delete_duplicates(self, head: ListNode) -> ListNode:
        """
        Time: O(n), Space: O(1)
        :param head:
        :return:
        """
        if not head:
            return

        cur, next = head, head.next
        while next:
            if cur.val == next.val:
                cur.next = next.next
            else:
                cur = next
            next = next.next

        return head