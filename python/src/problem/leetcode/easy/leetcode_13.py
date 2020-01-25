# coding=utf-8


class Solution:
    """
    罗马数字转阿拉伯数字
    """

    mapping = {
        "I": 1,
        "V": 5,
        "X": 10,
        "L": 50,
        "C": 100,
        "D": 500,
        "M": 1000
    }

    @staticmethod
    def roman_to_int(s: str) -> int:
        """
        Time: O(n), Space: O(1)
        :param s:
        :return:
        """
        result = Solution.mapping[s[len(s) - 1]]
        i = len(s) - 2
        while i >= 0:
            cur = Solution.mapping[s[i]]
            right = Solution.mapping[s[i + 1]]
            if cur >= right:
                result += cur
            else:
                result -= cur
            i -= 1
        return result

