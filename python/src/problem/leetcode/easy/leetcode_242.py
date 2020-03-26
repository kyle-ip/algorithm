# coding=utf-8

class Solution:
    """
    变位词校验
    """

    def is_anagram(self, s: str, t: str) -> bool:
        """

        :param s:
        :param t:
        :return:
        """

        if not s and not t:
            return True

        if len(s) != len(t):
            return False

        s, t = "".join(sorted(s)), "".join(sorted(t))
        for i in range(len(s)):
            if s[i] != t[i]:
                return False
        return True

    def is_anagram2(self, s: str, t: str) -> bool:
        """

        :param s:
        :param t:
        :return:
        """
        if not s and not t:
            return True

        if len(s) != len(t):
            return False

        counts = [0 for _ in range(26)]
        for i in range(len(s)):
            counts[ord(s[i]) - 97] += 1
            counts[ord(t[i]) - 97] -= 1
        for c in counts:
            if c != 0:
                return False
        return True
