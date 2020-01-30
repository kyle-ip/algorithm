# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    和为零的 N 个唯一整数
    """

    def sum_zero(self, n: int) -> List[int]:
        """
        Time: O(n), Space: O(1)
        :param n:
        :return:
        """
        if n < 1:
            return []
        res = [0 for _ in range(len(n))]
        for i in range(1, n):
            res[i] = i
        res[0] = - n * (n - 1) / 2

        return res