# coding=utf-8
from typing import List


class Solution:
    """
    字符串相加
    """

    def add_strings(self, num1: str, num2: str) -> str:
        """
        Time: O(m+n), Space: O(1)
        :param num1:
        :param num2:
        :return:
        """
        i, j, k, carry = len(num1) - 1, len(num2) - 1, max(len(num1), len(num2)), 0
        res = ["" for _ in range(k + 1)]
        while i >= 0 or j >= 0 or carry > 0:
            if i >= 0:
                carry += int(num1[i])
                i -= 1
            if j >= 0:
                carry += int(num2[j])
                j -= 1
            res[k] = str(carry % 10)
            k -= 1
            carry //= 10
        return "".join(res).strip()

