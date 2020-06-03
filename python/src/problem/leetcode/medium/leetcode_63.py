# coding=utf-8
from typing import List

from src.data_structure.data_structure import ListNode


class Solution:
    """
    路径数量（含障碍物）
    """

    def unique_paths_with_obstacles(self, obstacleGrid: List[List[int]]) -> int:
        """
        Time: O(m*n), Space: O(m*n)
        :param m:
        :param n:
        :return:
        """
        m, n = len(obstacleGrid), len(obstacleGrid[0])
        dp = [[0 for _ in range(n)] for _ in range(m)]
        dp[0][0] = 0 if obstacleGrid[0][0] == 1 else 1
        for i in range(1, m):
            dp[i][0] = 0 if obstacleGrid[i][0] == 1 else dp[i - 1][0]
        for j in range(1, n):
            dp[0][j] = 0 if obstacleGrid[0][j] == 1 else dp[0][j - 1]

        for i in range(1, m):
            for j in range(1, n):
                dp[i][j] = 0 if obstacleGrid[i][j] == 1 else dp[i - 1][j] + dp[i][j - 1]

        return dp[m - 1][n - 1]
