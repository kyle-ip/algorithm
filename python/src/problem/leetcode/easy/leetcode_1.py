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
        for i in range(len(nums)):
            for j in range(i + 1, len(nums)):
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
        for i in range(len(nums)):
            num2 = target - nums[i]
            if num2 in cache:
                return [cache[num2], i]
            cache[nums[i]] = i
        return []
