# coding=utf-8

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    判断二叉树是否平衡
    """

    def get_height(self, root: TreeNode) -> int:
        """

        :param root:
        :return:
        """
        if not root:
            return 0
        return max(self.get_height(root.left), self.get_height(root.right)) + 1

    def is_balanced_tree_top_down(self, root: TreeNode) -> bool:
        """
        Time: O(nlog(n)), Space: O(n)
        :param root:
        :return:
        """
        if not root:
            return True
        left, right = root.left, root.right
        return abs(self.get_height(left) - self.get_height(right)) < 2 \
            and self.is_balanced_tree_top_down(left) \
            and self.is_balanced_tree_top_down(right)

