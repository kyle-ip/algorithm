# coding=utf-8


class Solution:
    """
    报数
    """

    @staticmethod
    def count_and_say(n: int) -> str:
        """
        Time: O(1.3^n), Space: O(1.3^n)
        Time: O(2^n), Space: O(2^n)
        :param n:
        :return:
        """
        if n < 1:
            return

        s = "1"
        i = 1
        while i < n:
            idx, cnt = 0, 1
            while idx < len(s):
                if idx + 1 < len(s) and s[idx] == s[idx + 1]:
                    cnt += 1
                else:
                    s += "{0}{1}".format(cnt, idx)
                    cnt = 1
                idx += 1
            i += 1

        return s