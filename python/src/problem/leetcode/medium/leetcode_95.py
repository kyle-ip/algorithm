# coding=utf-8
from typing import List
from src.data_structure.data_structure import TreeNode


class Solution:
    """
    用 1~n 生成二叉搜索树
    """

    def clone_tree(self, root: TreeNode):
        """

        :param root:
        :return:
        """
        if not root:
            return
        return TreeNode(root.val, root.left, root.right)

    def gen_trees(self, low: int, high: int) -> List[TreeNode]:
        """

        :param low:
        :param high:
        :return:
        """
        if low > high:
            return [None, ]
        ret = []
        for i in range(low, high + 1):
            left_list, right_list = self.gen_trees(low, i - 1), self.gen_trees(i + 1, high)
            for left in left_list:
                for right in right_list:
                    ret.append(TreeNode(i, self.clone_tree(left), self.clone_tree(right)))
        return ret

    def generate_trees(self, n: int) -> List[TreeNode]:
        """
        Time/Space: O(4^n / n^(3/2))     第 n 项卡特兰数
        :param n:
        :return:
        """
        return self.gen_trees(1, n) if n > 0 else []
