package com.ywh.problem.leetcode.easy;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

/**
 * 测试移除有序数组中的重复元素
 * {@link LeetCode26}
 *
 * @author ywh
 * @since 2019/11/8/008
 */
@DisplayName("测试移除有序数组中的重复元素")
class LeetCode26Test {

    private static LeetCode26 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode26();
    }

    @ParameterizedTest
    @DisplayName("测试移除有序数组中的重复元素")
    @CsvSource({
        "'1,2,2,3,4,4', 4, '1,2,3,4'",
        "'2,2,3,3,4,4', 3, '2,3,4'",
        "'', 0, ''",
    })
    void testRemoveDuplicates(ArgumentsAccessor arguments) {
        int[] arr = StringUtil.strToIntArray(arguments.getString(0));
        int expectLen = arguments.getInteger(1);
        int[] expectArr = StringUtil.strToIntArray(arguments.getString(2));
        int res = solution.removeDuplicates(arr);

        assertEquals(expectLen, res);
        assertEquals(StringUtil.intArrayToStr(expectArr), StringUtil.intArrayToStr(arr, res));
    }
}
