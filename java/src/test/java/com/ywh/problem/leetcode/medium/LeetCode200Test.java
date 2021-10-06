package com.ywh.problem.leetcode.medium;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试小岛数量
 * {@link LeetCode200}
 *
 * @author ywh
 * @since 11/11/2019
 */
@DisplayName("测试小岛数量")
class LeetCode200Test {
    private static LeetCode200 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode200();
    }

    @Test
    @DisplayName("测试输入：0")
    void testZero() {
        char[][] grid = new char[][]{
            {'0', '0', '0'},
            {'0', '0', '0'}
        };
        assertEquals(0, solution.numIslands(grid));
    }

    @Test
    @DisplayName("测试输入：1")
    void testOne() {
        char[][] grid = new char[][]{
            {'0', '0', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        assertEquals(1, solution.numIslands(grid));
    }

    @Test
    @DisplayName("测试输入")
    void testNormal() {
        char[][] grid = new char[][]{
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        assertEquals(3, solution.numIslands(grid));
    }

}
