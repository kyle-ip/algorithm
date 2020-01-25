# coding=utf-8

from typing import List


class Solution:
    """
    数组加一
    """

    @staticmethod
    def plus_one(digits: List[int]) -> List[int]:
        """
        Time: O(n), Space: O(1)
        :param digits:
        :return:
        """

        p = len(digits) - 1
        while p >= 0 and digits[p] == 9:
            digits[p] = 0
            p -= 1
        if p == -1:
            digits.insert(0, 1)
            return digits
        digits[p] += 1
        return digits
