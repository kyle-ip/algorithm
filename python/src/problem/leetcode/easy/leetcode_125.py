# coding=utf-8

class Solution:
    """
    回文字符串判断
    """

    @staticmethod
    def is_palindrome(s: str) -> bool:
        """
        Time: O(n), Space: O(1)
        :param s:
        :return:
        """
        if not s:
            return True
        left, right = 0, len(s) - 1
        while left < right:
            while left < right and not (s[right].isalpha() or s[right].isdigit()):
                right -= 1
            while left < right and not (s[left].isalpha() or s[left].isdigit()):
                left += 1
            if left < right and s[left].lower() != s[right].lower():
                return False
            left += 1
            right -= 1
        return True
