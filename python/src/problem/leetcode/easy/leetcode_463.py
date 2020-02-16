# coding=utf-8
from typing import List


class Solution:
    """
    岛屿的周长
    """

    def is_land_perimeter(self, grid: List[List[int]]) -> int:
        """
        Time: O(n^2), Space: O(n)
        :param grid:
        :return:
        """

        if not grid or not grid[0]:
            return 0

        m, n, perimeter = len(grid), len(grid[0]), 0

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 0:
                    continue
                perimeter += 4
                if i - 1 >= 0 and grid[i - 1][j] == 1:
                    perimeter -= 1
                if i + 1 < m and grid[i + 1][j] == 1:
                    perimeter -= 1
                if j - 1 >= 0 and grid[i][j - 1] == 1:
                    perimeter -= 1
                if j + 1 < n and grid[i][j + 1] == 1:
                    perimeter -= 1
        return perimeter

