# coding=utf-8
from typing import List


class Solution:
    """
    三角形中的最小路径和
    """

    def minimum_total(self, triangle: List[List[int]]) -> int:
        """
        Time: O(n^2), Space: O(n)
        :param triangle:
        :return:
        """
        n = len(triangle)
        d = [[0 for _ in range(n)] for _ in range(n)]
        for j in range(n):
            d[n - 1][j] = triangle[n - 1][j]
        for i in range(n - 2, -1, -1):
            for j in range(i + 1):
                d[i][j] = min(d[i + 1][j], d[i + 1][j + 1]) + triangle[i][j]
        return d[0][0]
