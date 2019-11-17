package com.ywh.problem.leetcode.medium;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试 0/1 矩阵中的最大正方形
 * {@link LeetCode221}
 *
 * @author ywh
 * @since 12/11/2019
 */
@DisplayName("测试 0/1 矩阵中的最大正方形")
public class LeetCode221Test {

    private static LeetCode221 solution;

    private final char[][] matrix0 = new char[][]{};
    private final char[][] matrix1 = new char[][]{
        {'1','0','1','0','0'},
        {'1','0','1','1','1'},
        {'1','1','1','1','1'},
        {'1','0','0','1','0'}
    };
    private final char[][] matrix2 = new char[][]{
        {'1','0','1','1','1'},
        {'1','1','1','1','1'},
        {'1','1','1','1','1'},
        {'1','0','0','1','0'}
    };
    private final char[][] matrix3 = new char[][]{
        {'1','0','1','0','0'},
        {'1','1','1','1','1'},
        {'0','0','0','1','1'},
    };

    @BeforeAll
    static void init() {
        solution = new LeetCode221();
    }

    @Test
    @DisplayName("测试分层单调栈解法")
    void testMaximalSquareHistogram() {
        assertEquals(0, solution.maximalSquareHistogram(null));
        assertEquals(0, solution.maximalSquareHistogram(matrix0));
        assertEquals(4, solution.maximalSquareHistogram(matrix1));
        assertEquals(9, solution.maximalSquareHistogram(matrix2));
        assertEquals(4, solution.maximalSquareHistogram(matrix3));
    }

    @Test
    @DisplayName("测试动态规划解法")
    void testMaximalSquareDP() {
        assertEquals(0, solution.maximalSquareDP(null));
        assertEquals(0, solution.maximalSquareDP(matrix0));
        assertEquals(4, solution.maximalSquareDP(matrix1));
        assertEquals(9, solution.maximalSquareDP(matrix2));
        assertEquals(4, solution.maximalSquareDP(matrix3));
    }

    @Test
    @DisplayName("测试动态规划解法（优化存储空间）")
    void testMaximalSquareDPOn() {
        assertEquals(0, solution.maximalSquareDPOn(null));
        assertEquals(0, solution.maximalSquareDPOn(matrix0));
        assertEquals(4, solution.maximalSquareDPOn(matrix1));
        assertEquals(9, solution.maximalSquareDPOn(matrix2));
        assertEquals(4, solution.maximalSquareDPOn(matrix3));
    }
}
