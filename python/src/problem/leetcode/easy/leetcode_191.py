# coding=utf-8


class Solution:
    """
    位 1 的个数
    """

    def hamming_weight(self, n: int) -> int:
        """
        Time: O(k), Space: O(1)
        :param n:
        :return:
        """
        count = 0
        while n != 0:
            count += 1
            n &= (n - 1)
        return count
