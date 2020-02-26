# coding=utf-8
from typing import List


class Solution:
    """
    判断数独的有效性
    """

    def is_valid_sudoku(self, board: List[List[str]]) -> bool:
        """
        Time: O(n^2), Space: O(n^2)
        :param nums:
        :param target:
        :return:
        """

        for i in range(9):
            s = set()
            for j in range(9):
                if board[i][j] == ".":
                    continue
                if board[i][j] in s:
                    return False
                s.add(board[i][j])
            s = set()
            for j in range(9):
                if board[j][i] == ".":
                    continue
                if board[j][i] in s:
                    return False
                s.add(board[j][i])

            s = set()
            for j in range(9):
                r = 3 * (i // 3) + j // 3
                c = 3 * (i % 3) + j % 3
                if board[r][c] == ".":
                    continue
                if board[r][c] in s:
                    return False
                s.add(board[r][c])
        return True

    def is_valid_sudoku2(self, board: List[List[str]]) -> bool:
        """

        :param board:
        :return:
        """
        row_seen = [[False for _ in range(9)] for _ in range(9)]
        col_seen = [[False for _ in range(9)] for _ in range(9)]
        box_seen = [[False for _ in range(9)] for _ in range(9)]
        for i in range(9):
            for j in range(9):
                if board[i][j] == ".":
                    continue
                x = int(board[i][j]) - 1
                k = (i // 3) * 3 + j // 3
                if row_seen[i][x] or col_seen[j][x] or box_seen[k][x]:
                    return False
                row_seen[i][x] = col_seen[j][x] = box_seen[k][x] = True
        return True

