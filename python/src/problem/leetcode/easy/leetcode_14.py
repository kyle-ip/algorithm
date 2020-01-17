# coding=utf-8
from typing import List

class Solution:
    """
    字符串的最长公共前缀
    """

    @staticmethod
    def longest_common_prefix(strs: List[str]) -> str:
        """
        Time: O(k*n), Space: O(1)
        :param strs:
        :return:
        """
        if not strs or len(strs) == 0:
            return ""

        first = strs[0]
        i = 0
        while i < len(first):
            j = 1
            while j < len(strs):
                if len(strs[j]) <= i or strs[j][i] != first[i]:
                    return first[0: i]
                j += 1

            i += 1
        return first