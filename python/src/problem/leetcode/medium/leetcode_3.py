# coding=utf-8


class Solution:
    """
    无重复字符的最长子串
    """

    def length_of_longest_substring(self, s: str) -> int:
        """
        Time: O(n), Space: O(m)
        :param s:
        :return:
        """
        hash = set()
        max_length = left = right = 0
        while left < len(s) and right < len(s):
            if s[right] not in hash:
                hash.add(s[right])
                right += 1
                max_length = max(max_length, right - left)
            else:
                hash.remove(s[left])
                left += 1
        return max_length
