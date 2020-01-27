# coding=utf-8
from typing import List


class Solution:
    """
    验证完全平方数
    """

    def is_perfect_square(self, num: int) -> bool:
        """

        :param num:
        :return:
        """
        x = num
        while x * x > num:
            x = (x + num / x) / 2
        return x * x == num

