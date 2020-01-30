# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    二分查找
    """

    def search(self, nums: List[int], target: int) -> int:
        """
        Time: O(log(n)), Space: O(1)
        :param nums:
        :param target:
        :return:
        """

        if not nums or len(nums) == 0:
            return -1

        low, high = 0, len(nums) - 1
        while low <= high:
            mid = low + (high - low) // 2
            if nums[mid] == target:
                return mid
            if nums[mid] > target:
                high = mid - 1
            else:
                low = mid + 1
        return -1

