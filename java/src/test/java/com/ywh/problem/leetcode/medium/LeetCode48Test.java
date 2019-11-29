package com.ywh.problem.leetcode.medium;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * 测试旋转二维数组
 * {@link LeetCode48}
 *
 * @author ywh
 * @since 29/11/2019
 */
@DisplayName("测试矩阵的螺旋遍历")
public class LeetCode48Test {

    private static LeetCode48 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode48();
    }

    @Test
    public void testRotate() {
        int[][] matrix = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] expected = new int[][]{
            {7, 4, 1},
            {8, 5, 2},
            {9, 6, 3}
        };
        solution.rotate(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    public void testRotate2() {
        int[][] matrix = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] expected = new int[][]{
            {7, 4, 1},
            {8, 5, 2},
            {9, 6, 3}
        };
        solution.rotate2(matrix);
        assertArrayEquals(expected, matrix);
    }
}
