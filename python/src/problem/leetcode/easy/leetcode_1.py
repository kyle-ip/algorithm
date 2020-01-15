# coding=utf-8
import typing


class Solution:

    def two_sum(self, nums: typing.List[int], target: int) -> typing.List[int]:
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

    def two_sum2(self, nums: typing.List[int], target: int) -> typing.List[int]:
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
