# coding=utf-8
from typing import List


class Solution:
    """
    二分搜索插入位置
    """

    def search_insert(self, nums: List[int], target: int) -> int:
        """
        Time: O(log(n)), Space: O(1)
        :param nums:
        :param target:
        :return:
        """
        if len(nums) <= 0:
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
        return low
