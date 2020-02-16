# coding=utf-8


class Solution:
    """
    最长回文子串
    """

    def longest_palindrome_dp(self, s: str) -> str:
        """
        Time: O(n^2), Space: O(n^2)
        :param s:
        :return:
        """
        if not s:
            return ""
        start = max_len = 0
        dp = [[False for _ in range(len(s))] for _ in range(len(s))]
        for i in range(len(s) - 1, -1, -1):
            for j in range(i, len(s)):
                if i == j:
                    dp[i][j] = True
                elif i + 1 == j:
                    dp[i][j] = s[i] == s[j]
                else:
                    dp[i][j] = s[i] == s[j] and dp[i + 1][j - 1]

                if dp[i][j] and j - i + 1 > max_len:
                    max_len = j - i + 1
                    start = i
        return s[start:start + max_len]

    def expend(self, s: str, left: int, right: int) -> int:
        """
        :param s:
        :param left:
        :param right:
        :return:
        """
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left, right = left - 1, right + 1
        return (right - 1) - (left + 1) + 1

    def longest_palindrome_expand(self, s: str) -> str:
        """
        Time: O(n^2), Space: O(1)
        :param s:
        :return:
        """
        if not s:
            return ""
        start = max_len = 0
        for i in range(len(s)):
            cur_len = max(self.expend(s, i, i), self.expend(s, i, i + 1))
            if cur_len > max_len:
                max_len = cur_len
                start = i - (cur_len - 1) // 2
        return s[start: start + max_len]
