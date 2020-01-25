# coding=utf-8

from typing import List
import sys


class Solution:
    """
    连续子序列的最大和
    """

    @staticmethod
    def max_sub_array(nums: List[int]) -> int:
        """
        Time: O(n), Space: O(1)
        :param nums:
        :return:
        """

        if not nums:
            return 0
        max_num, cur = -sys.maxsize, 0
        for num in nums:
            if cur > 0:
                cur += num
            else:
                cur = num
            max_num = max(max_num, cur)
        return max_num
