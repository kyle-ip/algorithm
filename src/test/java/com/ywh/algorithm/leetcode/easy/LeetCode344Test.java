package com.ywh.algorithm.leetcode.easy;

import com.ywh.algorithm.leetcode.easy.LeetCode344;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

class LeetCode344Test {

    private static LeetCode344 solution;


    @BeforeAll
    static void init() {
        solution = new LeetCode344();
    }


    @ParameterizedTest
    @CsvSource({
        "abcde, edcba",
        "abcd, dcba"
    })
    void testReverseString(ArgumentsAccessor arguments) {
        String expected = arguments.getString(1);
        String actual = solution.reverseString(arguments.getString(0));
        assertEquals(expected, actual);
    }
}