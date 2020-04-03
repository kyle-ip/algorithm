# coding=utf-8


class Solution:
    """
    阶乘末尾 0 的个数
    """

    def trailing_zeroes(self, n: int) -> int:
        """
        Time: O(log_5(n)), Space: O(1)
        :param n:
        :return:
        """
        cnt = 0
        while n > 0:
            n /= 5
            cnt += n
        return cnt
