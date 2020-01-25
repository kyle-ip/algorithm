# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    用有序数组构建二叉搜索树
    """

    def sorted_array_to_bst(self, nums: List[int], start: int, end: int) -> TreeNode:
        """

        :param nums:
        :param start:
        :param end:
        :return:
        """
        if start > end:
            return None
        mid = start + (end - start) // 2
        root = TreeNode(nums[mid])
        root.left = self.sorted_array_to_bst(nums, 0, mid - 1)
        root.right = self.sorted_array_to_bst(nums, mid + 1, end)
        return root

    def sorted_array_to_bst_recursive(self, nums: List[int]) -> TreeNode:
        """
        TODO 暂时未解决
        Time: O(n), Space: O(log(n))
        :param nums:
        :return:
        """
        if not nums or len(nums) == 0:
            return None
        return self.sorted_array_to_bst(nums, 0, len(nums) - 1)

