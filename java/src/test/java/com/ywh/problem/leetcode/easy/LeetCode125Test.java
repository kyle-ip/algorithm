package com.ywh.problem.leetcode.easy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 测试判断回文串
 * {@link LeetCode125}
 *
 * @author ywh
 * @since 2/13/2019
 */
@DisplayName("测试判断回文串")
class LeetCode125Test {

    private static LeetCode125 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode125();
    }

    @Test
    @DisplayName("测试空字符串")
    void testEmptyTrue() {
        assertTrue(solution.isPalindrome(""));
    }

    @ParameterizedTest
    @DisplayName("测试仅含字母数字的字符串")
    @CsvSource({
        "AbccBa", "ndu1221UDn"
    })
    void testAlphanumericTrue(String s) {
        assertTrue(solution.isPalindrome(s));
    }

    @ParameterizedTest
    @DisplayName("测试仅含字母数字的字符串")
    @CsvSource({
        "AbccBacfa"
    })
    void testAlphanumericFalse(String s) {
        assertFalse(solution.isPalindrome(s));
    }


    @ParameterizedTest
    @DisplayName("[true] 测试一般字符串")
    @CsvSource({
        " race a E-car ",

    })
    void testTrue(String s) {
        assertTrue(solution.isPalindrome(s));
    }

    @ParameterizedTest
    @DisplayName("[false] 一般字符串")
    @CsvSource({
        " race a car "
    })
    void testFalse(String s) {
        assertFalse(solution.isPalindrome(s));
    }
}