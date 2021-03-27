package com.ywh.problem.leetcode.hard;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试最长连续整数序列的长度
 * {@link LeetCode128}
 *
 * @author ywh
 * @since 2019/11/12/012
 */
@DisplayName("测试最长连续整数序列的长度")
class LeetCode128Test {

    private static LeetCode128 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode128();
    }

    @ParameterizedTest
    @DisplayName("测试排序解法")
    @CsvSource({
        "'0', 1",
        "'100,4,200,1,3,2', 4",
        "'', 0",
        "'1,2,0,1', 3",
        "'8,4,2,1,2,3,6', 4"
    })
    void testLengthOfLongestConsecutiveSequenceSorting(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.lengthOfLongestConsecutiveSequenceSorting(nums));
    }

    @ParameterizedTest
    @DisplayName("测试哈希表解法")
    @CsvSource({
        "'0', 1",
        "'100,4,200,1,3,2', 4",
        "'', 0",
        "null, 0",
        "'1,2,0,1', 3",
        "'8,4,2,1,2,3,6', 4"
    })
    void testLengthOfLongestConsecutiveSequenceSet(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.lengthOfLongestConsecutiveSequenceSet1(nums));
    }

}
