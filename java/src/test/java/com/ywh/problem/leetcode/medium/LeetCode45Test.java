package com.ywh.problem.leetcode.medium;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

/**
 * 测试跳完数组的最少跳数
 * {@link LeetCode45}
 *
 * @author ywh
 * @since 29/11/2019
 */
@DisplayName("测试跳完数组的最少跳数")
public class LeetCode45Test {

    private static LeetCode45 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode45();
    }

    @ParameterizedTest
    @CsvSource({
        "'2,4,0,1,2', 2",
        "'2,1,0,4', -1",
        "'0', 0",
        "'null', -1",
        "'', -1"
    })
    void testCanJumpToLast(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.numOfJumpToLast(nums));
    }

    @ParameterizedTest
    @CsvSource({
        "'2,4,0,1,2', 2",
        "'2,1,0,4', -1",
        "'0', 0",
        "'null', -1",
        "'', -1"
    })
    void testNumOfJumpToLastO1(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.numOfJumpToLastO1(nums));
    }
}
