# coding=utf-8
from src.data_structure.data_structure import TreeNode


class Solution:
    """
    另一个树的子树
    """

    def is_same_tree(self, s: TreeNode, t: TreeNode) -> bool:
        """

        :param s:
        :param t:
        :return:
        """
        if not s and not t:
            return True

        if not s or not t:
            return False

        return s.val == t.val \
               and self.is_same_tree(s.left, t.left) \
               and self.is_same_tree(s.right, t.right)

    def is_subtree(self, s: TreeNode, t: TreeNode) -> bool:
        """

        :param s:
        :param t:
        :return:
        """

        if not t:
            return True

        if not s:
            return False

        return self.is_same_tree(s, t) \
               or self.is_subtree(s.left, t) \
               or self.is_subtree(s.right, t)

