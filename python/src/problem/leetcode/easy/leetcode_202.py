# coding=utf-8
from typing import List


class Solution:
    """
    快乐数
    """

    def transform(self, n: int) -> int:
        """

        :param n:
        :return:
        """
        s = 0
        while n != 0:
            a = n % 10
            s += a * a
            n //= 10
        return s


    def is_happy(self, n: int) -> bool:
        """
        Time: O(1), Space: O(1)
        :param n:
        :return:
        """
        if n <= 0:
            return False
        fast = slow = n
        while True:
            fast = self.transform(self.transform(fast))
            slow = self.transform(slow)
            if fast == 1:
                return True
            if fast == slow:
                return False

