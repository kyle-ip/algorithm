# coding=utf-8


class Solution:
    """
    反转字符串中的单词 III
    """

    def reverse_words(self, s: str) -> str:
        """

        :param s:
        :return:
        """
        if not s and len(s) == 0:
            return s
        start = end = 0
        s = [i for i in s]
        while start < len(s):
            while end < len(s) and s[end] != ' ':
                end += 1
            i, j = start, end - 1
            while i < j:
                s[i], s[j] = s[j], s[i]
                i += 1
                j -= 1
            start = end + 1
            end = start

        return "".join(s)
