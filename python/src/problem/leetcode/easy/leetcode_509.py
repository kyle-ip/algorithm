# coding=utf-8


class Solution:
    """
    斐波那契数
    """

    def fib(self, N: int) -> int:
        """

        :param N:
        :return:
        """
        if N <= 0:
            return 0
        if N == 1:
            return 1
        dp = [0 for _ in range(N + 1)]
        dp[0], dp[1] = 0, 1
        for i in range(2, N + 1):
            dp[i] = dp[i - 1] + dp[i - 2]

        return dp[N]
