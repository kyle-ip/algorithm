# coding=utf-8
from typing import List

class Solution:
    """
    有效的括号序列
    """

    @staticmethod
    def is_valid_brackets(s: str) -> bool:
        """
        Time: O(n), Space: O(n)
        :param s:
        :return:
        """
        stack = []
        i = 0
        while i < len(s):
            if s[i] in "([{":
                stack.append(s[i])
            elif len(stack) == 0:
                return False
            else:
                if s[i] == ")" and stack[-1] != "(" \
                        or s[i] == "]" and stack[-1] != "[" \
                        or s[i] == "{" and stack[-1] != "}":
                    return False
                stack.pop()
            i += 1
        return len(stack) == 0

    @staticmethod
    def is_valid_brackets2(s: str) -> bool:
        """
        Time: O(n), Space: O(n)
        :param s:
        :return:
        """
        stack = []
        i = 0
        while i < len(s):
            if s[i] == "(":
                stack.append(")")
            elif s[i] == "[":
                stack.append("]")
            elif s[i] == "{":
                stack.append("}")
            else:
                if len(stack) == 0 or s[i] != stack.pop():
                    return False

            i += 1
        return len(stack) == 0
