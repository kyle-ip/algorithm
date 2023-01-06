package com.ywh.problem.leetcode.easy;

import com.ywh.problem.leetcode.easy.LeetCode13;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试罗马数字转阿拉伯数字
 * {@link LeetCode13}
 *
 * @author ywh
 * @since 19/11/2019
 */
@DisplayName("测试罗马数字转阿拉伯数字")
public class LeetCode13Test {

    private static LeetCode13 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode13();
    }

    @ParameterizedTest
    @CsvSource({
            "'III', 3",
            "'IV', 4",
            "'IX', 9",
            "'LVIII', 58",
            "'LXVI', 66",
            "'MCMXCIV', 1994",
            "'MMCMXCIX', 2999"
    })
    void testRomanToInt(ArgumentsAccessor arguments) {
        String str = arguments.getString(0);
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.romanToInt(str));
    }
}
