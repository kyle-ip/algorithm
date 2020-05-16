# coding=utf-8
from typing import List


class Solution:
    """
    最长摆动子序列的长度
    """

    def wiggle_max_length(self, nums: List[int]) -> int:
        """
        Time: O(n), Space: O(1)

        :param nums:
        :return:
        """
        if not nums:
            return 0
        i = l = 1
        while i < len(nums) and nums[i] == nums[i - 1]:
            i += 1
        while i < len(nums):
            if i < len(nums) and nums[i] > nums[i - 1]:
                l += 1
            while i < len(nums) and nums[i] >= nums[i - 1]:
                i += 1
            if i < len(nums) and nums[i] < nums[i - 1]:
                l += 1
            while i < len(nums) and nums[i] <= nums[i - 1]:
                i += 1
        return l

    def wiggle_max_length2(self, nums: List[int]) -> int:
        """
        Time: O(n), Space: O(1)

        :param nums:
        :return:
        """
        if not nums:
            return 0
        up = down = 1
        for i in range(1, len(nums)):
            if nums[i] > nums[i - 1]:
                up = down + 1
            if nums[i] < nums[i - 1]:
                down = up + 1
        return max(up, down)