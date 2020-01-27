# coding=utf-8
import math


class Solution:
    """
    计数质数
    """

    def count_primes(self, n: int) -> int:
        """
        Time: O(n*log(log(n))), Space: O(n)
        :param n:
        :return:
        """

        if n <= 2:
            return 0
        primes = [True for _ in range(n)]
        primes[0] = primes[1] = False

        for i in range(2, int(n ** (1 / 2)) + 1):
            if not primes[i]:
                continue
            j = i * i
            while j < n:
                primes[j] = False
                j += i
        cnt = 0
        for prime in primes:
            if prime:
                cnt += 1
        return cnt


if __name__ == "__main__":
    s = Solution()
    print(s.count_primes(10))
