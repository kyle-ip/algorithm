# coding=utf-8
from typing import List


class Solution:
    """
    移除数组中指定数字
    """

    def remove_element(self, nums: List[int], val: int) -> int:
        """
        Time: O(n), Space: O(1)
        :param nums:
        :param val:
        :return:
        """
        if not nums:
            return 0

        left = right = 0
        while right < len(nums):
            if nums[right] != val:
                nums[left] = nums[right]
                left += 1
            right += 1
        return left
