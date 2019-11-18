package com.ywh.problem.leetcode.hard;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * 测试滑动窗口中的最大值
 * {@link LeetCode239}
 *
 * @author ywh
 * @since 11/11/2019
 */
@DisplayName("测试滑动窗口中的最大值")
class LeetCode239Test {

    private static LeetCode239 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode239();
    }

    @ParameterizedTest
    @DisplayName("测试暴力解法")
    @CsvSource({
        "'0,4,2,1,0,8,2', 3, '4,4,2,8,8'"
    })
    void testMaxNumInSlidingWindowBruteForce(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int k = arguments.getInteger(1);
        int[] expected = StringUtil.strToIntArray(arguments.getString(2));
        assertArrayEquals(expected, solution.maxNumInSlidingWindowBruteForce(nums, k));
    }

    @ParameterizedTest
    @DisplayName("测试分组辅助数组解法")
    @CsvSource({
        "'0,4,2,1,0,8,2', 3, '4,4,2,8,8'"
    })
    void testMaxNumInSlidingWindowOn(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int k = arguments.getInteger(1);
        int[] expected = StringUtil.strToIntArray(arguments.getString(2));
        assertArrayEquals(expected, solution.maxNumInSlidingWindowOn(nums, k));
    }

    @ParameterizedTest
    @DisplayName("测试红黑树解法")
    @CsvSource({
        "'0,4,2,1,0,8,2', 3, '4,4,2,8,8'"
    })
    void testMaxNumInSlidingWindowTreeMap(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int k = arguments.getInteger(1);
        int[] expected = StringUtil.strToIntArray(arguments.getString(2));
        assertArrayEquals(expected, solution.maxNumInSlidingWindowTreeMap(nums, k));
    }
}
