# coding=utf-8

from src.data_structure.data_structure import ListNode


class Solution:
    """
    链表的相交节点
    """

    def get_intersection_node_without_len(self, headA: ListNode, headB: ListNode) -> ListNode:
        """
        Time: O(m+n), Space: O(1)
        :param headA:
        :param headB:
        :return:
        """
        if not headA or not headB:
            return
        pa, pb = headA, headB
        while pa != pb:
            pa = pa.next if pa else headB
            pb = pb.next if pb else headA
        return pa

    def get_intersection_node_with_len(self, headA: ListNode, headB: ListNode) -> ListNode:
        """
        Time: O(m+n), Space: O(1)
        :param headA:
        :param headB:
        :return:
        """
        if not headA or not headB:
            return

        len_a, len_b = 0, 0
        pa, pb = headA, headB
        while pa:
            pa = pa.next
            len_a += 1
        while pb:
            pb = pb.next
            len_b += 1
        delta = abs(len_a - len_b)

        pa, pb = headA, headB

        i = 0
        while i < delta and len_a > len_b:
            i += 1
            pa = pa.next
        i = 0
        while i < delta and len_b > len_a:
            i += 1
            pb = pb.next

        while pa and pb and pa != pb:
            pa = pa.next
            pb = pb.next

        if not pa or not pb:
            return

        return pa