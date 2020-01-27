# coding=utf-8
from typing import List


class Solution:
    """
    字符串中的第一个唯一字符
    """

    def first_uniq_char(self, s: str) -> int:
        counter = {}
        for c in s:
            if c not in counter:
                counter[c] = 1
            else:
                counter[c] += 1

        for i in range(len(s)):
            if counter[s[i]] == 1:
                return i
        return -1

