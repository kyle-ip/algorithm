package com.ywh.problem.leetcode.medium;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试旋转有序数组的最小值
 * {@link LeetCode153}
 *
 * @author ywh
 * @since 26/11/2019
 */
@DisplayName("测试旋转有序数组的最小值")
public class LeetCode153Test {

    private static LeetCode153 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode153();
    }

    @ParameterizedTest
    @DisplayName("测试二分搜索解法")
    @CsvSource({
        "'1,2,4,8,0', 0",
        "'2,4,8,0,1', 0",
        "'4,8,0,1,2', 0",
        "'8,0,1,2,4', 0",
        "'0,1,2,4,8', 0"
    })
    void testFindMin(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int min = arguments.getInteger(1);
        assertEquals(min, solution.findMin(nums));
    }

    @ParameterizedTest
    @DisplayName("测试二分搜索（提早返回）解法")
    @CsvSource({
        "'1,2,4,8,0', 0",
        "'2,4,8,0,1', 0",
        "'4,8,0,1,2', 0",
        "'8,0,1,2,4', 0",
        "'0,1,2,4,8', 0"
    })
    void testFindMinEarlyReturn(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int min = arguments.getInteger(1);
        assertEquals(min, solution.findMinEarlyReturn(nums));
    }
}
