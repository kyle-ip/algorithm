# coding=utf-8
from typing import List


class Solution:
    """
    最长回文串的长度
    """

    def longest_palindrome(self, s: str) -> int:
        """
        Time: O(n), Space: O(n)

        :param s:
        :return:
        """

        counter = {}
        for i in s:
            if i in counter:
                counter[i] += 1
            else:
                counter[i] = 1
        odd_count = 0
        for count in counter.values():
            if count % 2 == 1:
                odd_count += 1

        return len(s) - max(odd_count - 1, 0)
