# coding=utf-8
from typing import List


class Solution:
    """
    矩阵的螺旋遍历
    """

    def spiral_order(self, matrix: List[List[int]]) -> None:
        """
        Time: O(m*n), Space: O(1)
        :param matrix:
        :return:
        """
        if not matrix or not matrix[0]:
            return []

        ret = []
        top, bottom, left, right = 0, len(matrix) - 1, 0, len(matrix[0]) - 1
        while True:
            for i in range(left, right + 1):
                ret.append(matrix[top][i])
            top += 1
            if top > bottom:
                break
            for i in range(top, bottom + 1):
                ret.append(matrix[i][right])
            right -= 1
            if right < left:
                break
            for i in range(right, left - 1, -1):
                ret.append(matrix[bottom][i])
            bottom -= 1
            if bottom < top:
                break
            for i in range(bottom, top - 1, -1):
                ret.append(matrix[i][left])
            left += 1
            if left > right:
                break
        return ret
