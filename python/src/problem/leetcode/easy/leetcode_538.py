# coding=utf-8
from src.data_structure.data_structure import TreeNode


class Solution:
    """
    把二叉搜索树转换为累加树
    """

    def dfs(self, root: TreeNode, s: int) -> int:
        """
        :param root:
        :param s:
        :return:
        """
        if not root:
            return s
        root.val += self.dfs(root.right, s)
        return self.dfs(root.left, root.val)

    def convert_bst(self, root: TreeNode) -> TreeNode:
        """
        Time: O(n), Space: O(n)
        :param root:
        :return:
        """
        self.dfs(root, 0)
        return root

