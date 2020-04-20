# coding=utf-8
from typing import List


class Solution:
    """
    子数组求和是否为 K 的整数倍
    """

    def check_subarray_sum(self, nums: List[int], k: int) -> bool:
        """
        Time: O(n^2), Space: O(1)
        :param nums:
        :param k:
        :return:
        """
        if not nums or len(nums) < 2:
            return False

        for i in range(len(nums) - 1):
            s = nums[i]
            for j in range(i + 1, len(nums)):
                s += nums[j]
                if (k == 0 and s == 0) or (k != 0 and s % k == 0):
                    return True
        return False


    def check_subarray_sum2(self, nums: List[int], k: int) -> bool:
        """
        Time: O(n), Space: O(k)
        :param nums:
        :param k:
        :return:
        """
        if not nums or len(nums) < 2:
            return False
        cache = {0: -1}
        s = 0
        for i in range(len(nums)):
            s += nums[i]
            mod = s if k == 0 else s % k
            j = cache.get(mod)

            # 注意 0 也会返回 False
            if j is not None:
                if i - j > 1:
                    return True
            else:
                cache[mod] = i
        return False
