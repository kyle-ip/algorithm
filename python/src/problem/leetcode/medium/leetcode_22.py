# coding=utf-8
from typing import List
import sys

from src.data_structure.data_structure import ListNode


class Solution:
    """
    括号生成
    """

    def generate(self, res: List[str], s: str, left: int, right: int):
        """

        :param res:
        :param s:
        :param left:
        :param right:
        :return:
        """
        if left == 0 and right == 0:
            res.append(s)
        else:
            if left > 0:
                self.generate(res, s + "(", left - 1, right)
            if right > left:
                self.generate(res, s + ")", left, right - 1)

    def generate_parenthesis(self, n: int) -> List[str]:
        """
        Time: O(4^n / sqrt(n)), Space: O(n)      卡特兰数
        :param n:
        :return:
        """
        res = []
        if n <= 0:
            return res
        self.generate(res, "", n, n)
        return res
