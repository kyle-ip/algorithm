package com.ywh.algorithm.leetcode.easy;

import com.ywh.algorithm.leetcode.easy.LeetCode125;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("测试判断回文串")
class LeetCode125Test {

    @Test
    @DisplayName("[true] 空字符串")
    void testEmptyTrue() {
        Assertions.assertTrue(LeetCode125.isPalindrome(""));
    }

    @ParameterizedTest
    @DisplayName("[true] 仅含字母数字的字符串")
    @CsvSource({
        "AbccBa",
        "ndu1221UDn"
    })
    void testAlphanumericTrue(String s) {
        Assertions.assertTrue(LeetCode125.isPalindrome(s));
    }

    @ParameterizedTest
    @DisplayName("[false] 仅含字母数字的字符串")
    @CsvSource({
        "AbccBacfa"
    })
    void testAlphanumericFalse(String s) {
        Assertions.assertFalse(LeetCode125.isPalindrome(s));
    }


    @ParameterizedTest
    @DisplayName("[true] 一般字符串")
    @CsvSource({
        " race a E-car "
    })
    void testTrue(String s) {
        Assertions.assertTrue(LeetCode125.isPalindrome(s));
    }

    @ParameterizedTest
    @DisplayName("[false] 一般字符串")
    @CsvSource({
        " race a car "
    })
    void testFalse(String s) {
        Assertions.assertFalse(LeetCode125.isPalindrome(s));
    }
}