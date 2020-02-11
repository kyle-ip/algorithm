# coding=utf-8
from typing import List


class Solution:
    """
    盛最多水的容器
    """

    def max_area(self, height: List[int]) -> int:
        """
        Time: O(n), Space: O(1)
        :param height:
        :return:
        """

        left, right = 0, len(height) - 1
        max_a = 0
        while left < right:
            s = (right - left) * min(height[left], height[right])
            max_a = max(max_a, s)
            if height[left] < height[right]:
                left += 1
            else:
                right -= 1
        return max_a
