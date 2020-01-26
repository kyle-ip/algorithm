# coding=utf-8


class Solution:
    """
    Excel 表列序号
    """

    def title_to_number(self, s: str) -> int:
        """
        Time: O(n), Space: O(1)
        :param s:
        :return:
        """
        base, num = 1, 0
        for i in range(len(s), 0, -1):
            num += (ord(s[i - 1]) - 64) * base
            base *= 26
        return num