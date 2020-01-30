# coding=utf-8
from src.data_structure.data_structure import TreeNode


class Solution:
    """
    另一个树的子树
    """

    def num_jewels_in_stones(self, J: str, S: str) -> int:
        """
        利用 Hash 标记珠宝的位置
        :param J:
        :param S:
        :return:
        """
        if not J or not S or len(J) == 0 or len(S) == 0:
            return 0

        hash = [False for _ in range(256)]

        for i in range(len(J)):
            hash[ord(J[i]) - 64] = True

        count = 0
        for i in range(len(S)):
            if hash[ord(S[i]) - 64]:
                count += 1
        return count
