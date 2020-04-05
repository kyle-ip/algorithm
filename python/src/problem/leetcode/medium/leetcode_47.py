# coding=utf-8
from typing import List, Set, Tuple


class Solution:
    """
    数组的全排列
    """

    def permute_rec(self, nums: List[int], start: int, result: Set[Tuple[int]]):
        """

        :param nums:
        :param start:
        :param result:
        :return:
        """
        if start == len(nums):
            result.add(tuple(nums))
            return
        for i in range(start, len(nums)):
            nums[start], nums[i] = nums[i], nums[start]
            self.permute_rec(nums, start + 1, result)
            nums[start], nums[i] = nums[i], nums[start]

    def permute_unique_using_set(self, nums: List[int]) -> List[List[int]]:
        """
        Time: O(n*n!), Space: O(n)
        :param nums:
        :return:
        """
        if not nums:
            return []

        result = set()
        self.permute_rec(nums, 0, result)
        return list(result)

    