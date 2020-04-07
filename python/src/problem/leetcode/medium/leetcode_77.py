# coding=utf-8
from typing import List


class Solution:
    """
    数字组合
    """

    def combine(self, n: int, k: int, start: int, elem: List[int], ret: List[List[int]]):
        """

        :param n:
        :param k:
        :param start:
        :param elem:
        :param ret:
        :return:
        """
        if k == 0:
            ret.append(list(elem))
            return
        for i in range(start, n - k + 2):
            elem.append(i)
            self.combine(n, k - 1, i + 1, elem, ret)
            elem.pop()

    def combine_recursive(self, n: int, k: int) -> List[List[int]]:
        """

        :param n:
        :param k:
        :return:
        """
        if k > n:
            return []
        ret, elem = [], []
        self.combine(n, k, 1, elem, ret)
        return ret
