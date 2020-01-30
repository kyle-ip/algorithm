# coding=utf-8
from src.data_structure.data_structure import TreeNode


class Solution:
    """
    平方数之和
    """

    def judge_square_sum(self, c: int) -> bool:
        """
        Time: O(c ^ 1/2), Space: O(1)
        :param c:
        :return:
        """
        i, j = 0, int(c ** (1 / 2))
        while i <= j:
            s = i * i + j * j
            if s == c:
                return True
            elif s < c:
                i += 1
            else:
                j -= 1
        return False

