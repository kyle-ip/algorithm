# coding=utf-8


class Solution:

    @staticmethod
    def strstr(haystack: str, needle: str) -> int:
        """
        Time: O((n-m+1)*m), Space: O(1)
        :param haystack:
        :param needle:
        :return:
        """
        i, n, m = 0, len(haystack), len(needle)
        while i <= n - m:
            j, k = 0, i
            while j < m and k < n and haystack[k] == needle[j]:
                j += 1
                k += 1
            if j == m:
                return i
            i += 1
        return -1