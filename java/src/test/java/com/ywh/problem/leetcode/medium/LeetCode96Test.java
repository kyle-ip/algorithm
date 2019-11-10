package com.ywh.problem.leetcode.medium;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试二叉搜索树的数量
 * {@link LeetCode96}
 *
 * @author ywh
 * @since 10/11/2019
 */
@DisplayName("测试二叉搜索树的数量")
class LeetCode96Test {

    private static LeetCode96 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode96();
    }

    @ParameterizedTest
    @DisplayName("测试动态规划解法")
    @CsvSource({
        "0, 1",
        "1, 1",
        "2, 2",
        "3, 5",
        "4, 14",
        "5, 42",
        "6, 132",
        "7, 429"
    })
    void testNumTreesDP(ArgumentsAccessor arguments) {
        int num = arguments.getInteger(0);
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.numTreesDP(num));
    }

    @ParameterizedTest
    @DisplayName("测试组合数学解法")
    @CsvSource({
        "0, 1",
        "1, 1",
        "2, 2",
        "3, 5",
        "4, 14",
        "5, 42",
        "6, 132",
        "7, 429"
    })
    void testNumTreesMath(ArgumentsAccessor arguments) {
        int num = arguments.getInteger(0);
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.numTreesMath(num));
    }
}
