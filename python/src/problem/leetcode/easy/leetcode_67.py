# coding=utf-8

from typing import List


class Solution:
    """
    二进制字符串求和
    """

    def add_binary(self, a: str, b: str) -> str:
        """
        Time: O(max(m, n)), Space: O(max(m, n))
        :param a:
        :param b:
        :return:
        """
        ret = ""
        idx_a, idx_b, carry = len(a) - 1, len(b) - 1, 0
        while idx_a >= 0 or idx_b >= 0 or carry != 0:
            s = carry
            if idx_a >= 0:
                s += int(a[idx_a])
                idx_a -= 1
            if idx_b >= 0:
                s += int(b[idx_b])
                idx_b -= 1
            ret = str(s % 2) + ret
            carry = s // 2
        return ret
