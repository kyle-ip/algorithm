# coding=utf-8
from typing import List


class Solution:
    """
    旋转图像
    """

    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Time: O(n^2), Space: O(1)
        :param matrix:
        :return:
        """
        if not matrix or not matrix[0]:
            return

        for i in range(len(matrix)):
            for j in range(i, len(matrix)):
                matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]

        for i in range(len(matrix)):
            for j in range(len(matrix) // 2):
                matrix[i][j], matrix[i][len(matrix) - 1 - j] = matrix[i][len(matrix) - 1 - j], matrix[i][j]

