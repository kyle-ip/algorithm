package com.ywh.problem.leetcode.medium;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试验证入栈出栈序列
 * {@link LeetCode946}
 *
 * @author ywh
 * @since 26/11/2019
 */
@DisplayName("测试验证入栈出栈序列")
public class LeetCode946Test {

    private static LeetCode946 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode946();
    }

    @ParameterizedTest
    @DisplayName("测试辅助栈解法")
    @CsvSource({
        "'1,2,3,4,5', '4,5,3,2,1', true",
        "'1,2,3,4,5', '3,2,1,4,5', true",
        "'1,2,3,4,5', '1,2,3,4,5', true",
        "'1,2,3,4,5', '4,5,3,1,2', false",
        "'1,2,3,4,5', '4,3,5,1,2', false",
    })
    void testValidateStackSequences(ArgumentsAccessor arguments) {
        int[] pushed = StringUtil.strToIntArray(arguments.getString(0));
        int[] popped = StringUtil.strToIntArray(arguments.getString(1));
        boolean expected = arguments.getBoolean(2);
        assertEquals(expected, solution.validateStackSequences(pushed, popped));
    }

    @ParameterizedTest
    @DisplayName("测试辅助栈（数组实现）解法")
    @CsvSource({
        "'1,2,3,4,5', '4,5,3,2,1', true",
        "'1,2,3,4,5', '3,2,1,4,5', true",
        "'1,2,3,4,5', '1,2,3,4,5', true",
        "'1,2,3,4,5', '4,5,3,1,2', false",
        "'1,2,3,4,5', '4,3,5,1,2', false",
    })
    void testValidateStackSequencesArray(ArgumentsAccessor arguments) {
        int[] pushed = StringUtil.strToIntArray(arguments.getString(0));
        int[] popped = StringUtil.strToIntArray(arguments.getString(1));
        boolean expected = arguments.getBoolean(2);
        assertEquals(expected, solution.validateStackSequencesArray(pushed, popped));
    }
}
