# coding=utf-8
from typing import List


class Solution:
    """
    存在重复元素
    """

    def contains_duplicate(self, nums: List[int]) -> bool:
        """
        Time: O(n), Space: O(n)
        :param nums:
        :return:
        """
        s = set()
        for num in nums:
            if num in s:
                return True
            s.add(num)
        return False
