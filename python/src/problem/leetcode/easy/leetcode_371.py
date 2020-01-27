# coding=utf-8
from typing import List


class Solution:
    """
    两整数之和
    """

    def get_sum(self, a: int, b: int) -> int:
        """
        FIXME 暂时未解决

        Time: O(m), Space: O(1)
        :param a:
        :param b:
        :return:
        """

        return a if b == 0 else self.get_sum(a ^ b, (a & b) << 1)