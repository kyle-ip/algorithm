# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    非减数组
    """

    def check_possibility_boolean(self, nums: List[int]) -> bool:
        """
        Time: O(n), Space: O(1)

        :param nums:
        :return:
        """
        if not nums:
            return False
        if len(nums) == 1:
            return True
        modified = True if nums[0] > nums[1] else False
        for i in range(1, len(nums) - 1):
            if nums[i] <= nums[i + 1]:
                continue
            if modified:
                return False
            # 4, [5], 3 => 4, 5, 5
            if nums[i + 1] < nums[i - 1]:
                nums[i + 1] = nums[i]

            # 3, [5], 4 => 3, 3, 4
            else:
                nums[i] = nums[i + 1]

            modified = True
        return True
