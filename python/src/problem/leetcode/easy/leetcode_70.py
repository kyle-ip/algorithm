# coding=utf-8


class Solution:
    """
    爬楼梯方法数
    """

    def climb_stairs_recursive(self, n: int) -> int:
        """
        Time: O(n), Space: O(n)
        :param n:
        :return:
        """

        if n < 2:
            return 1
        else:
            return self.climb_stairs_recursive(n - 1) + \
                   self.climb_stairs_recursive(n - 2)


    def climb_stairs_iterative(self, n: int) -> int:
        """
        Time: O(n), Space: O(1)
        :param n:
        :return:
        """

        x0, x1 = 1, 1
        for _ in range(n - 1):
            x2 = x0 + x1
            x0 = x1
            x1 = x2
        return x1

