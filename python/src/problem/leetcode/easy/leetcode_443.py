# coding=utf-8
from typing import List


class Solution:
    """
    压缩字符串
    """

    def compress(self, chars: List[str]) -> int:
        """
        Time: O(n), Space: O(1)
        :param chars:
        :return:
        """
        anchor = write = 0
        for read, char in enumerate(chars):
            if read + 1 == len(chars) or chars[read + 1] != char:
                chars[write] = chars[anchor]
                write += 1
                if read > anchor:
                    for digit in str(read - anchor + 1):
                        chars[write] = digit
                        write += 1
                anchor = read + 1
        return write