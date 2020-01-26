# coding=utf-8
from typing import List


class Solution:
    """
    帕斯卡三角形
    """

    def generate_pascal_triangle(self, numRows: int) -> List[List[int]]:
        """
        Time: O(n^2), Space: O(1)
        :param numRows:
        :return:
        """

        result = []
        if numRows < 1:
            return result

        for i in range(numRows):
            row = [0 for _ in range(i + 1)]
            row[0] = row[i] = 1
            for j in range(1, i):
                row[j] = result[i - 1][j - 1] + result[i - 1][j]
            result.append(row)
        return result