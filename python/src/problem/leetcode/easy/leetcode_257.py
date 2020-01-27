# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    二叉树的所有路径
    """

    def binary_tree_paths(self, root: TreeNode, cur: str, res: List[str]) -> None:
        """
        
        :param root: 
        :param cur: 
        :param res: 
        :return: 
        """
        if not root:
            return
        cur += str(root.val)
        if not root.left and not root.right:
            res.append(cur)
        self.binary_tree_paths(root.left, cur + "->", res)
        self.binary_tree_paths(root.right, cur + "->", res)

    def binary_tree_paths(self, root: TreeNode) -> List[str]:
        """
        Time: O(n), Space: O(n)
        :param root:
        :return:
        """
        res = []
        if not root:
            return res
        self.binary_tree_paths(root, "", res)
        return res