# coding=utf-8

import sys


class Solution:

    def reverse(self, x: int) -> int:
        """
        Time: O(k), Space: O(1)
        :param x:
        :return:
        """
        is_negative = False
        if x < 0:
            x = -x
            is_negative = True
        y = 0
        while x != 0:
            y = y * 10 + x % 10
            x //= 10

        # 对结果判断是否越界
        if y < -2 ** 31 or y > 2 ** 31 - 1:
            return 0
        elif is_negative:
            return -y
        else:
            return y

