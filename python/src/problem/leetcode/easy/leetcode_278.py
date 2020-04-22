# coding=utf-8


class Solution:
    """
    第一个出错的版本
    """

    def is_bad_version(self, n: int) -> bool:
        """

        :param n:
        :return:
        """
        return True


    def first_bad_version(self, n: int) -> int:
        """

        :param n:
        :return:
        """
        low, high = 1, n
        while low < high:
            mid = low + (high - low) // 2
            if self.is_bad_version(mid):
                high = mid
            else:
                low = mid
        return low