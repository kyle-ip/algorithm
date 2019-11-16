package com.ywh.problem.leetcode.hard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试 0/1 矩阵中的最大矩形
 *
 * {@link LeetCode84}
 *
 * @author ywh
 * @since 16/11/2019
 */
@DisplayName("测试 0/1 矩阵中的最大矩形")
public class LeetCode85Test {

    private static LeetCode85 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode85();
    }

    @Test
    void test1() {
        char[][] matrix = new char[][]{};
        assertEquals(0, solution.maximalRectangle(null));
        assertEquals(0, solution.maximalRectangle(matrix));
    }

    @Test
    void test2() {
        char[][] matrix = new char[][]{
            {'1','0','1','0','0'},
            {'1','1','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        int expected = 10;
        assertEquals(expected, solution.maximalRectangle(matrix));
    }

    @Test
    void test3() {
        char[][] matrix = new char[][]{
            {'1','0','1','0','0'},
            {'1','1','1','1','1'},
            {'0','0','1','1','1'},
        };
        int expected = 6;
        assertEquals(expected, solution.maximalRectangle(matrix));
    }



    @Test
    void test4() {
        char[][] matrix = new char[][]{
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        int expected = 6;
        assertEquals(expected, solution.maximalRectangle(matrix));
    }
}
