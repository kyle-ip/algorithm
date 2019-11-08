package com.ywh.problem.leetcode.easy;

import com.ywh.problem.leetcode.medium.LeetCode167;
import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertArrayEquals;

/**
 * 测试有序数组中求和为给定值的两个数
 * {@link LeetCode167}
 *
 * @author ywh
 * @since 2/13/2019
 */
@DisplayName("测试有序数组中求和为给定值的两个数")
class LeetCode167Test {

    private static LeetCode167 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode167();
    }

    @ParameterizedTest
    @DisplayName("[true] 有序数组中求和为给定值的两个数")
    @CsvSource({
        "'1,2,3,6,8,11', 10, '2,5'",
    })
    void testGetTwoSumToTarget1(
        ArgumentsAccessor arguments
    ) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int target = arguments.getInteger(1);
        int[] expect = StringUtil.strToIntArray(arguments.getString(2));
        assertArrayEquals(expect, solution.getTwoSumToTarget(nums, target));

    }
}