# coding=utf-8
from typing import List


class Solution:
    """
    求和为给定值的两个数
    """

    @staticmethod
    def two_sum(nums: List[int], target: int) -> List[int]:
        """
        Time: O(n^2), Space: O(1)
        :param nums:
        :param target:
        :return:
        """

        i = 0
        while i < len(nums):
            j = i + 1
            if nums[i] + nums[j] == target:
                return [i, j]
        return []

    @staticmethod
    def two_sum2(nums: List[int], target: int) -> List[int]:
        """
        Time: O(n), Space: O(n)
        :param nums:
        :param target:
        :return:
        """
        cache = {}
        i = 0
        while i < len(nums):
            num2 = target - nums[i]
            if num2 in cache.keys():
                return [cache.get(num2), i]
            cache[nums[i]] = i
            i += 1
        return []
