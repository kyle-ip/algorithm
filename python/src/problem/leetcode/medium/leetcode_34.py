# coding=utf-8
from typing import List


class Solution:
    """
    有序数组中查找数字的开始和结束下标
    """

    def find_first_and_last_position(self, nums: List[int], target: int) -> List[int]:
        """
        Time: O(n), Space: O(1)
        :param nums:
        :param target:
        :return:
        """
        if not nums:
            return [-1, -1]
        start = end = -1
        for i in range(0, len(nums)):
            if start == -1 and nums[i] == target:
                start = i
            if nums[i] == target:
                end = i
        return [start, end]

    def binary_search_last_one(self, nums: List[int], target: int) -> int:
        """

        :param nums:
        :param target:
        :return:
        """
        low, high = 0, len(nums) - 1
        while low <= high:
            mid = low + (high - low) // 2
            if nums[mid] > target:
                high = mid - 1
            else:
                low = mid + 1
        return high

    def binary_search_first_and_last_position(self, nums: List[int], target: int) -> List[int]:
        """
        Time: O(n), Space: O(1)
        :param nums:
        :param target:
        :return:
        """
        if not nums:
            return [-1, -1]
        end = self.binary_search_last_one(nums, target)
        start = self.binary_search_last_one(nums, target - 1) + 1
        if 0 <= start <= end < len(nums):
            return [start, end]
        return [-1, -1]