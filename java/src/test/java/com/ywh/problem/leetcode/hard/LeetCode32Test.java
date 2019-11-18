package com.ywh.problem.leetcode.hard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试有效括号的最大长度
 * {@link LeetCode32}
 *
 * @author ywh
 * @since 11/11/2019
 */
@DisplayName("测试有效括号的最大长度")
class LeetCode32Test {

    private static LeetCode32 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode32();
    }

    @ParameterizedTest
    @DisplayName("测试动态规划解法")
    @CsvSource({
        "'(((', 0",
        "'()', 2",
        "'(()', 2",
        "'(()()', 4",
        "'(())', 4",
        "')()())', 4",
        "')(()())', 6",
        "'()()())', 6",
        "')(()())', 6"
    })
    void testMaxLengthOfValidParenthesesDP(ArgumentsAccessor arguments) {
        String str = arguments.getString(0);
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.maxLengthOfValidParenthesesDP(str));
    }

    @ParameterizedTest
    @DisplayName("测试栈解法")
    @CsvSource({
        "'(((', 0",
        "'()', 2",
        "'(()', 2",
        "'(()()', 4",
        "'(())', 4",
        "')()())', 4",
        "')(()())', 6",
        "'()()())', 6",
        "')(()())', 6"
    })
    void testMaxLengthOfValidParenthesesStack(ArgumentsAccessor arguments) {
        String str = arguments.getString(0);
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.maxLengthOfValidParenthesesStack(str));
    }
}
