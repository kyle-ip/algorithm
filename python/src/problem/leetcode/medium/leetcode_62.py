# coding=utf-8
from typing import List

from src.data_structure.data_structure import ListNode


class Solution:
    """
    路径数量
    """

    def unique_paths_dp(self, m: int, n: int) -> int:
        """
        Time: O(m*n), Space: O(m*n)
        :param m:
        :param n:
        :return:
        """
        if m < 1 or n < 1:
            return 0
        dp = [[0 for _ in range(n)] for _ in range(m)]
        for i in range(m):
            dp[i][0] = 1
        for j in range(n):
            dp[0][j] = 1
        for i in range(1, m):
            for j in range(1, n):
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        return dp[m - 1][n - 1]