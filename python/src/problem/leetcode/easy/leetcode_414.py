# coding=utf-8
from typing import List
import sys

class Solution:
    """
    第三大的数
    """

    def third_max(self, nums: List[int]) -> int:
        """
        Time: O(n), Space: O(1)
        :param nums:
        :return:
        """
        third = second = first = -sys.maxsize
        flag = True
        change_count = 0

        for num in nums:
            if num == -sys.maxsize and flag:
                change_count += 1
                flag = False
            if num > first:
                change_count += 1
                third = second
                second = first
                first = num
            elif first > num > second:
                change_count += 1
                third = second
                second = num
            elif second > num > third:
                change_count += 1
                third = num

        return third if change_count >= 3 else first


