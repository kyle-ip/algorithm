# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    三维形体的表面积
    """

    def surface_area(self, grid: List[List[int]]) -> int:
        """
        Time: O(n^2), Space: O(1)
        :param grid:
        :return:
        """
        res, n = 0, len(grid)
        for i in range(n):
            for j in range(n):
                if grid[i][j] > 0:
                    res += 6 + (grid[i][j] - 1) * 4

                if i - 1 >= 0 and grid[i - 1][j] > 0:
                    res -= min(grid[i][j], grid[i - 1][j])

                if i + 1 < n and grid[i + 1][j] > 0:
                    res -= min(grid[i][j], grid[i + 1][j])

                if j - 1 >= 0 and grid[i][j - 1] > 0:
                    res -= min(grid[i][j], grid[i][j - 1])

                if j + 1 < n and grid[i][j + 1] > 0:
                    res -= min(grid[i][j], grid[i][j + 1])

        return res
