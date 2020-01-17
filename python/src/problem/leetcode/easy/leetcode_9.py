# coding=utf-8

class Solution:
    """
    回文数字判断
    """

    @staticmethod
    def is_palindrome(x: int) -> bool:
        """
        Time: O(n), Space: O(1)
        :param x:
        :return:
        """
        if x < 0:
            return False
        s = str(x)
        left, right = 0, len(s) - 1
        while left < right:
            if s[left] != s[right]:
                return False
            left += 1
            right -= 1
        return True

    def is_palindrome2(self, x: int) -> bool:
        """
        Time: O(n), Space: O(1)
        :param x:
        :return:
        """
        if x < 0:
            return False
        y, tmp = 0, x
        while tmp != 0:
            y = y * 10 + tmp % 10
            tmp //= 10
        return y == x
