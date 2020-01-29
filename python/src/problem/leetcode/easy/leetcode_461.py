# coding=utf-8
from typing import List


class Solution:
    """
    汉明距离
    """

    def hamming_distance(self, x: int, y: int) -> int:
        """
        Time: O(n), Space: O(1)
        :param x:
        :param y:
        :return:
        """
        count, n = 0, x ^ y
        while n != 0:
            count += 1
            n &= (n - 1)

        return count



