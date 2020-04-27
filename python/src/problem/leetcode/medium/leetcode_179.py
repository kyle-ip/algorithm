# coding=utf-8
from typing import List
from functools import cmp_to_key


class Solution:
    """
    拼接的最大数字
    """

    def largest_number(self, nums: List[int]) -> str:
        """

        :param nums:
        :return:
        """

        s = [str(i) for i in nums]
        s.sort(key=cmp_to_key(lambda x, y: int(x + y) - int(y + x)), reverse=True)
        return "0" if s[0] == "0" else "".join(s)


if __name__ == "__main__":
    t = Solution()
    print(t.largest_number([0, 0]))
