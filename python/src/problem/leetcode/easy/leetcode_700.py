# coding=utf-8
from src.data_structure.data_structure import TreeNode


class Solution:
    """
    另一个树的子树
    """

    def search_bst(self, root: TreeNode, val: int) -> TreeNode:
        """
        Time: O(h), Space: O(1)
        :param root:
        :param val:
        :return:
        """
        while root and root.val != val:
            root = root.right if root.val < val else root.left
        return root

