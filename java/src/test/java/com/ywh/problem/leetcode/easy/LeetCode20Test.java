package com.ywh.problem.leetcode.easy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

/**
 * 测试有效的括号序列
 * {@link LeetCode20}
 *
 * @author ywh
 * @since 2019/11/8/008
 */
@DisplayName("测试有效的括号序列")
class LeetCode20Test {

    private static LeetCode20 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode20();
    }

    @ParameterizedTest
    @DisplayName("测试逐位入栈解法")
    @CsvSource({
        "'{[()]}', 'true'",
        "'{{{}}}', 'true'",
        "'{}{', 'false'"
    })
    void testIsValidBrackets(ArgumentsAccessor arguments) {
        String str = arguments.getString(0);
        boolean expect = arguments.getBoolean(1);
        assertEquals(expect, solution.isValidBrackets(str));
    }

    @ParameterizedTest
    @DisplayName("测试对称括号入栈解法")
    @CsvSource({
        "'{[()]}', 'true'",
        "'{{{}}}', 'true'",
        "'{}{', 'false'"
    })
    void testIsValidBracketsShort(ArgumentsAccessor arguments) {
        String str = arguments.getString(0);
        boolean expect = arguments.getBoolean(1);
        assertEquals(expect, solution.isValidBracketsShort(str));
    }
}
