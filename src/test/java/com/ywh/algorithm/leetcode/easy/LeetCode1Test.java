//package com.ywh.algorithm.leetcode.easy;
//
//import com.ywh.algorithm.util.StringUtil;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
//import org.junit.jupiter.params.provider.CsvSource;
//
//import static org.junit.Assert.assertArrayEquals;
//
//@DisplayName("测试两数之和等于给定值")
//class LeetCode1Test {
//
//    private static LeetCode1 solution;
//
//    @BeforeAll
//    static void init() {
//        solution = new LeetCode1();
//    }
//
//    @ParameterizedTest
//    @DisplayName("[true] 测试双重循环方法")
//    @CsvSource({
//        "'[1, 1]', 2, '[0, 1]'",
//        "'[3, 2, 3]', 6, '[0, 2]'",
//        "'[2, 7, 11, 15]', 9, '[0, 1]'",
//        "'[2, 7, 11, 15]', 0, '[-1, -1]'"
//    })
//    void testGetTwoSumToTarget1(
//        ArgumentsAccessor arguments
//    ) {
//        int[] nums = StringUtil.stringToIntArray(arguments.getString(0));
//        int target = arguments.getInteger(1);
//        int[] expect = StringUtil.stringToIntArray(arguments.getString(2));
//        assertArrayEquals(expect, solution.getTwoSumToTarget1(nums, target));
//    }
//
//    @ParameterizedTest
//    @DisplayName("[true] 测试 Map 缓存方法")
//    @CsvSource({
//        "'[1, 1]', 2, '[0, 1]'",
//        "'[3, 2, 3]', 6, '[0, 2]'",
//        "'[2, 7, 11, 15]', 9, '[0, 1]'",
//        "'[2, 7, 11, 15]', 0, '[-1, -1]'"
//    })
//    void testGetTwoSumToTarget2(
//        ArgumentsAccessor arguments
//    ) {
//        int[] nums = StringUtil.stringToIntArray(arguments.getString(0));
//        int target = arguments.getInteger(1);
//        int[] expect = StringUtil.stringToIntArray(arguments.getString(2));
//        assertArrayEquals(expect, solution.getTwoSumToTarget2(nums, target));
//    }
//}