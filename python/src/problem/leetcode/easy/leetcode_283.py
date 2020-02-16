# coding=utf-8
from typing import List


class Solution:
    """
    移动零
    """

    def move_zeroes(self, nums: List[int]) -> None:
        """
        Time: O(n), Space: O(1)
        :param nums:
        :return:
        """
        if not nums:
            return
        slow = 0
        for fast in range(len(nums)):
            if nums[fast] != 0:
                nums[slow] = nums[fast]
                slow += 1
        for i in range(slow, len(nums)):
            nums[i] = 0