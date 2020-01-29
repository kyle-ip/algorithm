# coding=utf-8
from typing import List


class Solution:
    """
    缺失的所有数字
    """

    def find_disappeared_numbers(self, nums: List[int]) -> List[int]:
        """
        Time: O(n), Space: O(n)
        :param nums:
        :return:
        """
        if not nums or len(nums) == 0:
            return []
        res = []
        existed = [False for _ in range(len(nums) + 1)]
        for num in nums:
            existed[num] = True

        for i in range(1, len(nums)):
            if not existed[i]:
                res.append(i)

        return res