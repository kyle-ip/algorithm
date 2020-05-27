# coding=utf-8
from typing import List


class Solution:
    """
    跳数组
    """

    def can_jump_to_last(self, nums: List[int]) -> bool:
        """
        Time: O(n), Space: O(1)
        :param nums:
        :return:
        """
        if not nums:
            return False
        max_idx = 0
        for i in range(len(nums)):
            if max_idx >= len(nums) - 1:
                return True
            if max_idx < i:
                return False
            max_idx = max(max_idx, i + nums[i])
        return False
