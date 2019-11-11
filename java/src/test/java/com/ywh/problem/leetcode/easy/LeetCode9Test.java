package com.ywh.problem.leetcode.easy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

/**
 * 测试回文数字判断
 * {@link LeetCode9}
 *
 * @author ywh
 * @since 2019/11/8/008
 */
@DisplayName("测试回文数字判断")
class LeetCode9Test {

    private static LeetCode9 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode9();
    }

    @ParameterizedTest
    @DisplayName("测试字符串双指针解法")
    @CsvSource({
        "12321, 'true'",
        "123321, 'true'",
        "0, 'true'",
        "123333, 'false'"
    })
    void testIsPalindromeString(ArgumentsAccessor arguments) {
        int num = arguments.getInteger(0);
        boolean expected = arguments.getBoolean(1);
        assertEquals(expected, solution.isPalindromeString(num));
    }

    @ParameterizedTest
    @DisplayName("测试取模解法")
    @CsvSource({
        "12321, 'true'",
        "123321, 'true'",
        "0, 'true'",
        "123333, 'false'"
    })
    void testIsPalindrome(ArgumentsAccessor arguments) {
        int num = arguments.getInteger(0);
        boolean expected = arguments.getBoolean(1);
        assertEquals(expected, solution.isPalindrome(num));
    }
}
