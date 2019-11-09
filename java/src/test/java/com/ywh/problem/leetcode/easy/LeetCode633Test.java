package com.ywh.problem.leetcode.easy;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试两个完全平方数的和
 *
 * @author ywh
 * @since 09/11/2019
 */
@DisplayName("测试两个完全平方数的和")
class LeetCode633Test {

    private static LeetCode633 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode633();
    }

    @ParameterizedTest
    @DisplayName("测试两个完全平方数的和")
    @CsvSource({
        "2450, 'true'",
        "8, 'true'",
        "0, 'true'",
        "3, 'false'",
        "9999, 'false'"
    })
    void testGetTwoSumToTarget1(
        ArgumentsAccessor arguments
    ) {
        int num = arguments.getInteger(0);
        boolean expect = arguments.getBoolean(1);
        assertEquals(expect, solution.judgeSquareSum(num));
        assertEquals(expect, solution.judgeSquareSumHashSet(num));
        assertEquals(expect, solution.judgeSquareSumMath(num));
        assertEquals(expect, solution.judgeSquareSumTwoPointer(num));
//        solution.judgeSquareSum();
    }

}
