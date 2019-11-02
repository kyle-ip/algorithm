package com.ywh.problem.leetcode.easy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

class LeetCode557Test {


    private static LeetCode557 solution;


    @BeforeAll
    static void init() {
        solution = new LeetCode557();
    }


    @ParameterizedTest
    @CsvSource({
        "'I am busy.', 'I ma .ysub'"
    })
    void testReverseWords(ArgumentsAccessor arguments) {
        String expected = arguments.getString(1);
        String actual = solution.reverseWords(arguments.getString(0));
        assertEquals(expected, actual);
    }
}