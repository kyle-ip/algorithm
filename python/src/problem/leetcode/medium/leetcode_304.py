# coding=utf-8
from typing import List


class Solution:
    prefix_sum = [[]]

    def __init__(self, matrix: List[List[int]]):
        """

        :param matrix:
        """
        if not matrix or not matrix[0]:
            self.prefix_sum = [[0, 0], [0, 0]]
            return
        m, n = len(matrix), len(matrix[0])
        self.prefix_sum = [[0 for _ in range(n + 1)] for _ in range(m + 1)]
        for i in range(m + 1):
            for j in range(n + 1):
                self.prefix_sum[i][j] = self.prefix_sum[i][j - 1] + self.prefix_sum[i - 1][j] - self.prefix_sum[i - 1][j - 1] + matrix[i - 1][j - 1]

    def sum_region(self, row1: int, col1: int, row2: int, col2: int) -> int:
        """

        :param row1:
        :param col1:
        :param row2:
        :param col2:
        :return:
        """
        return self.prefix_sum[row2 + 1][col2 + 1] - self.prefix_sum[row2 + 1][col1] - self.prefix_sum[row1][col2 + 1] + self.prefix_sum[row1][col1]