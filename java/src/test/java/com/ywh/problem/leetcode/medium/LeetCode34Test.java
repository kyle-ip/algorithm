package com.ywh.problem.leetcode.medium;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertArrayEquals;

/**
 * 测试有序数组中查找数字的开始和结束下标
 * {@link LeetCode34}
 *
 * @author ywh
 * @since 29/11/2019
 */
@DisplayName("测试有序数组中查找数字的开始和结束下标")
class LeetCode34Test {
    private static LeetCode34 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode34();
    }

    @ParameterizedTest
    @DisplayName("测试一次循环解法")
    @CsvSource({
        "'1,2,2,4,4,8,8', 2, '1,2'",
        "'1,2,2,4,4,8,8', 1, '0,0'",
        "'1,2,2,4,4,8,8', 4, '3,4'",
        "'1,2,2,4,4,8,8', 8, '5,6'",
        "'1,2,2,4,4,8,8', 0, '-1,-1'",
        "'1,2,2,4,4,8,8', 6, '-1,-1'",
        "'1,2,2,4,4,8,8', 16, '-1,-1'"
    })
    void testFindFirstAndLastPosition(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int target = arguments.getInteger(1);
        int[] expected = StringUtil.strToIntArray(arguments.getString(2));
        assertArrayEquals(expected, solution.findFirstAndLastPosition(nums, target));
    }

    @ParameterizedTest
    @DisplayName("测试二分查找解法")
    @CsvSource({
        "'1,2,2,4,4,8,8', 2, '1,2'",
        "'1,2,2,4,4,8,8', 1, '0,0'",
        "'1,2,2,4,4,8,8', 4, '3,4'",
        "'1,2,2,4,4,8,8', 8, '5,6'",
        "'1,2,2,4,4,8,8', 0, '-1,-1'",
        "'1,2,2,4,4,8,8', 6, '-1,-1'",
        "'1,2,2,4,4,8,8', 16, '-1,-1'"
    })
    void testBinarySearchFirstAndLastPosition(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int target = arguments.getInteger(1);
        int[] expected = StringUtil.strToIntArray(arguments.getString(2));
        assertArrayEquals(expected, solution.binarySearchFirstAndLastPosition(nums, target));
    }
}
