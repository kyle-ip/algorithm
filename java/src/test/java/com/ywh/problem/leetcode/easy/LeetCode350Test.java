package com.ywh.problem.leetcode.easy;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertArrayEquals;

/**
 * 测试两个数组的交集
 * {@link LeetCode350}
 *
 * @author ywh
 * @since 20/11/2019
 */
@DisplayName("测试两个数组的交集")
class LeetCode350Test {
    private static LeetCode350 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode350();
    }

    @ParameterizedTest
    @DisplayName("测试哈希表解法")
    @CsvSource({
        "'', '', ''",
        "'1', '', ''",
        "'', '1', ''",
        "'1,4,4,2', '4,8,4,4', '4,4'",
        "'4,2,4', '1,4,4,2', '4,4,2'",
        "'1,4,4,2', '3,5,7', ''",
        "'4,2,1', '1,2,4', '1,2,4'"
    })
    void testIntersectHashMap(ArgumentsAccessor arguments) {
        int[] nums1 = StringUtil.strToIntArray(arguments.getString(0));
        int[] nums2 = StringUtil.strToIntArray(arguments.getString(1));
        int[] expected = StringUtil.strToIntArray(arguments.getString(2));
        assertArrayEquals(expected, solution.intersectHashMap(nums1, nums2));
    }

    @ParameterizedTest
    @DisplayName("测试排序解法")
    @CsvSource({
        "'', '', ''",
        "'1', '', ''",
        "'', '1', ''",
        "'1,4,4,2', '4,8,4,4', '4,4'",
        "'4,2,4', '1,4,4,2', '2,4,4'",
        "'1,4,4,2', '3,5,7', ''",
        "'4,2,1', '1,2,4', '1,2,4'"
    })
    void testIntersectSort(ArgumentsAccessor arguments) {
        int[] nums1 = StringUtil.strToIntArray(arguments.getString(0));
        int[] nums2 = StringUtil.strToIntArray(arguments.getString(1));
        int[] expected = StringUtil.strToIntArray(arguments.getString(2));
        assertArrayEquals(expected, solution.intersectSort(nums1, nums2));
    }
}