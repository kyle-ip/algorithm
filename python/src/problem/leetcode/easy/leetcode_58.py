# coding=utf-8


class Solution:
    """
    最后一个单词的长度
    """

    @staticmethod
    def length_of_last_word(s: str) -> int:
        """
        Time: O(n), Space: O(1)
        :param s:
        :return:
        """

        end, length = len(s) - 1, 0
        while end >= 0 and s[end] == " ":
            end -= 1
        if end < 0:
            return 0

        while end >= 0 and s[end] != " ":
            end -= 1
            length += 1

        return length