# coding=utf-8
from typing import List


class Solution:

    @staticmethod
    def remove_duplicates(nums: List[int]) -> int:
        """
        Time: O(n), Space: O(1)
        :param nums:
        :return:
        """
        if not nums:
            return 0
        right = left = 1
        while right < len(nums):
            if nums[right] != nums[right - 1]:
                nums[left] = nums[right]
                left += 1
            right += 1
        return left
