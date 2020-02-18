# coding=utf-8

from src.data_structure.data_structure import ListNode


class Solution:
    """
    Excel 表列名称
    """

    def convert_to_title(self, n: int) -> str:
        """
        Time: O(log_26(n)), Space: O(log_26(n))
        :param n:
        :return:
        """
        s = ""
        while n > 0:
            n -= 1
            s = chr(n % 26 + ord('A')) + s
            n //= 26
        return s