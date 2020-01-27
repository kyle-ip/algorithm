# coding=utf-8
from typing import List


class Solution:
    """
    同构字符串
    """

    def is_isomorphic(self, s: str, t: str) -> bool:
        """
        Time: O(1), Space: O(n)
        :param s:
        :param t:
        :return:
        """
        if not s and not t:
            return True
        if len(s) != len(t):
            return False
        hash1, hash2 = {}, {}
        for i in range(len(s)):
            if s[i] not in hash1:
                hash1[s[i]] = i
            if t[i] not in hash2:
                hash2[t[i]] = i
            if hash1[s[i]] != hash2[t[i]]:
                return False
        return True
