# coding=utf-8
from typing import List


class Solution:
    """
    3 的幂
    """

    def is_power_of_three(self, n: int) -> bool:
        """
        Time: O(log_3(n)), Space: O(1)
        :param n:
        :return:
        """

        if n <= 0:
            return False

        while n % 3 == 0:
            n /= 3

        return n == 1
