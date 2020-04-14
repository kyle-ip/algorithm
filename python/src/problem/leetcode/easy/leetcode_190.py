# coding=utf-8


class Solution:
    """
    翻转整数的二进制位
    """

    def reverse_bits(self, n: int) -> int:
        """
        Time: O(1), Space: O(1)

        :param n:
        :return:
        """
        ret = 0
        for i in range(32):
            ret = ret << 1 | (n & 1)
            n >>= 1
        return ret
