# coding=utf-8
from typing import List


class Solution:
    """
    电话号码对应的字母组合
    """

    mapping = [
        "abc", "def", "ghi", "jkl",
        "mno", "pqrs", "tuv", "wxyz"
    ]

    def combinations(self, digits: str, idx: int, s: str, result: List[str]):
        """

        :param digits:
        :param idx:
        :param s:
        :param result:
        :return:
        """
        if idx == len(digits):
            result.append(s)
            return
        chars = self.mapping[int(digits[idx]) - 2]
        for i in range(len(chars)):
            self.combinations(digits, idx + 1, s + chars[i], result)

    def letter_combinations_recursive(self, digits: str) -> List[int]:
        """
        Time: O(4^n), Space: O(n)

        :param nums:
        :return:
        """
        if not digits:
            return []
        result = []
        self.combinations(digits, 0, "", result)
        return result