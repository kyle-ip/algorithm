package com.ywh.problem.leetcode.easy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试阶乘末尾 0 的个数
 * {@link LeetCode172}
 *
 * @author ywh
 * @since 20/11/2019
 */
@DisplayName("测试阶乘末尾 0 的个数")
class LeetCode172Test {
    private static LeetCode172 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode172();
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "1, 0",
        "2, 0",
        "3, 0",
        "4, 0",
        "5, 1",
        "10, 2",
        "100, 24",
        "105, 25",
        "4617, 1151",
        "1808548329, 452137076"
    })
    void testTrailingZeroes(ArgumentsAccessor arguments) {
        int num = arguments.getInteger(0);
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.trailingZeroes(num));
    }
}