package com.ywh.problem.leetcode.easy;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试反转整数
 *
 * @author ywh
 * @since 09/11/2019
 */
@DisplayName("测试反转整数")
class LeetCode7Test {

    private static LeetCode7 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode7();
    }

    @ParameterizedTest
    @DisplayName("测试反转整数")
    @CsvSource({
        "9876, 6789",
        "-9876, -6789",
        "0987, 789",
        "0, 0"
    })
    void testGetTwoSumToTarget1(
        ArgumentsAccessor arguments
    ) {
        int num = arguments.getInteger(0);
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.reverse(num));
    }

}
