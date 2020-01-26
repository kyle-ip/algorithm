# coding=utf-8
from typing import List


class Solution:
    """
    帕斯卡三角形 2
    """

    def a(self, n: int, m: int) -> int:
        """
        排列数
        :param n:
        :param m:
        :return:
        """
        result = 1
        for i in range(m, 0, -1):
            result *= n
            n -= 1
        return result

    def c(self, n: int, m: int) -> int:
        """
        组合数
        :param n:
        :param m:
        :return:
        """
        return self.a(n, m) // self.a(m, m)

    def get_row(self, rowIndex: int) -> List[int]:
        result = []
        for i in range(rowIndex + 1):
            result.append(self.c(rowIndex, i))
        return result

    def get_row2(self, rowIndex: int) -> List[int]:
        result = []

        for i in range(rowIndex + 1):
            row = [0 for _ in range(i + 1)]
            row[0] = row[i] = 1
            for j in range(1, i):
                row[j] = result[i - 1][j - 1] + result[i - 1][j]
            result.append(row)
        return result.pop()

