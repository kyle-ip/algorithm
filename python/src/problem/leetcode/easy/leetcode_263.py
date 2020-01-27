# coding=utf-8
from typing import List


class Solution:
    """
    丑数
    """

    def is_ugly(self, num: int) -> bool:
        """

        :param num:
        :return:
        """

        if num <= 0:
            return False

        for i in [2, 3, 5]:
            while num % i == 0:
                num /= i

        return num == 1