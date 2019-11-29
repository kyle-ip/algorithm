package com.ywh.problem.leetcode.medium;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试跳数组
 * {@link LeetCode55}
 *
 * @author ywh
 * @since 29/11/2019
 */
@DisplayName("测试跳数组")
public class LeetCode55Test {

    private static LeetCode55 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode55();
    }

    @ParameterizedTest
    @CsvSource({
        "'2,4,0,1,2', true",
        "'2,1,0,4', false",
        "'0', true",
        "'null', false",
        "'', false"
    })
    void testCanJumpToLast(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        boolean expected = arguments.getBoolean(1);
        assertEquals(expected, solution.canJumpToLast(nums));
    }
}
