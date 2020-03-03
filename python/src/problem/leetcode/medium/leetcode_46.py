# coding=utf-8
from typing import List


class Solution:
    """
    数组的全排列
    """

    def permute_rec(self, nums: List[int], start: int, result: List[List[int]]):
        """

        :param nums:
        :param start:
        :param result:
        :return:
        """
        if len(nums) == start:
            result.append(list(nums))
            return
        for i in range(start, len(nums)):
            nums[i], nums[start] = nums[start], nums[i]
            self.permute_rec(nums, start + 1, result)
            nums[i], nums[start] = nums[start], nums[i]

    def permute(self, nums: List[int]) -> List[List[int]]:
        """
        Time: O(n*n!), Space: O(n)
        :param nums:
        :return:
        """
        result = []
        self.permute_rec(nums, 0, result)
        return result

