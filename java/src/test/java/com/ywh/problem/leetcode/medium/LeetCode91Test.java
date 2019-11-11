package com.ywh.problem.leetcode.medium;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试解码方式
 * {@link LeetCode91}
 *
 * @author ywh
 * @since 11/11/2019
 */
@DisplayName("测试解码方式")
class LeetCode91Test {
    private static LeetCode91 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode91();
    }

    @ParameterizedTest
    @DisplayName("测试递归解法")
    @CsvSource({
        "'0', 0",
        "'012', 0",
        "'120', 1",
        "'42', 1",
        "'12', 2",
        "'226', 3"
    })
    void testNumberOfDecodingsRecursive(ArgumentsAccessor arguments) {
        String str = arguments.getString(0);
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.numberOfDecodingsRecursive(str));
    }

    @ParameterizedTest
    @DisplayName("测试动态规划解法")
    @CsvSource({
        "'0', 0",
        "'012', 0",
        "'120', 1",
        "'42', 1",
        "'12', 2",
        "'226', 3"
    })
    void testNumberOfDecodingsDP(ArgumentsAccessor arguments) {
        String str = arguments.getString(0);
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.numberOfDecodingsDP(str));
    }

    @ParameterizedTest
    @DisplayName("测试动态规划解法（优化存储空间）")
    @CsvSource({
        "'0', 0",
        "'012', 0",
        "'120', 1",
        "'42', 1",
        "'12', 2",
        "'226', 3"
    })
    void testNumberOfDecodingsDPO1(ArgumentsAccessor arguments) {
        String str = arguments.getString(0);
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.numberOfDecodingsDPO1(str));
    }
}
