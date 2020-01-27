# coding=utf-8
from typing import List


class Solution:
    """
    反转字符串
    """

    def reverse_string(self, s: List[str]) -> None:
        """
        Time: O(n), Space: O(n)
        :param s:
        :return:
        """
        left, right = 0, len(s) - 1
        while left < right:
            s[left], s[right] = s[right], s[left]
            left += 1
            right -= 1

