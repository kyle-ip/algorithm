package com.ywh.problem.leetcode.easy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

/**
 * 测试反转字符串
 * {@link LeetCode344}
 *
 * @author ywh
 * @since 2/16/2019
 */
@DisplayName("测试反转字符串")
class LeetCode344Test {

    private static LeetCode344 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode344();
    }

    @ParameterizedTest
    @CsvSource({
        "abcde, edcba",
        "abcd, dcba"
    })
    void testReverseString(ArgumentsAccessor arguments) {
        String expected = arguments.getString(1);
        String actual = solution.reverseString(arguments.getString(0));
        assertEquals(expected, actual);
    }
}