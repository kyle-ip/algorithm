package com.ywh.problem.leetcode.easy;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试快乐数
 * {@link LeetCode202}
 *
 * @author ywh
 * @since 20/11/2019
 */
@DisplayName("测试快乐数")
public class LeetCode202Test {

    private final int ONE_MILLION = 1_000_000;

    private final int TEN_MILLION = 10_000_000;

    private static LeetCode202 solution;

    @BeforeAll
    public static void init() {
        solution = new LeetCode202();
    }

    @ParameterizedTest
    @DisplayName("测试哈希表解法")
    @CsvSource({
            "1, true",
            "7, true",
            "10, true",
            "28, true",
            "2, false",
            "4, false",
            "12, false",
            "16, false",
            "15999, false"
    })
    void testIsHappyHashSet(ArgumentsAccessor arguments) {
        int num = arguments.getInteger(0);
        boolean expected = arguments.getBoolean(1);
        assertEquals(expected, solution.isHappyHashSet(num));
    }

    @ParameterizedTest
    @DisplayName("测试数学解法")
    @CsvSource({
            "1, true",
            "7, true",
            "10, true",
            "28, true",
            "2, false",
            "4, false",
            "12, false",
            "16, false",
            "15999, false"
    })
    void testIsHappyMath(ArgumentsAccessor arguments) {
        int num = arguments.getInteger(0);
        boolean expected = arguments.getBoolean(1);
        assertEquals(expected, solution.isHappyMath(num));
    }

    @ParameterizedTest
    @DisplayName("测试双指针解法")
    @CsvSource({
            "1, true",
            "7, true",
            "10, true",
            "28, true",
            "2, false",
            "4, false",
            "12, false",
            "16, false",
            "15999, false"
    })
    void testIsHappyTwoPointer(ArgumentsAccessor arguments) {
        int num = arguments.getInteger(0);
        boolean expected = arguments.getBoolean(1);
        assertEquals(expected, solution.isHappyTwoPointer(num));
    }

    @Test
    public void testCrossValidation() {

        solution = new LeetCode202();

        for (int i = 0; i <= ONE_MILLION; ++i) {
            assertEquals(
                    solution.isHappyHashSet(i),
                    solution.isHappyTwoPointer(i));
            assertEquals(
                    solution.isHappyHashSet(i),
                    solution.isHappyMath(i));
        }
    }

    @Test
    public void testTransformationCount() {
        solution = new LeetCode202();

        int max = -1;
        // running about 9~10 min
        // int bound = Integer.MAX_VALUE;
        int bound = TEN_MILLION;
        for (int i = 1; i < bound; ++i) {
            max = Math.max(max, solution.isHappyHashSetCount(i));
        }
        System.out.println("max transformation count to terminate: " + max);

        max = -1;
        for (int i = 1; i < bound; ++i) {
            max = Math.max(max, solution.isHappyHashSetCountLessThanHundred(i));
        }
        System.out.println("max transformation count be <= 100: " + max);
    }

    @Test
    public void testPrintTransformationSeq() {
        solution.isHappyHashSetPrint(1999999999);
        solution.isHappyHashSetPrint(8888999);

        for (int i = 1; i <= 100; ++i) {
            solution.isHappyHashSetPrint(i);
        }

        for (int i = 1; i <= 100; ++i) {
            if (solution.isHappyTwoPointer(i)) {
                solution.isHappyHashSetPrint(i);
            }
        }
    }

    @Test
    public void benchmark() {

        solution = new LeetCode202();

        // warm up
        for (int i = 1; i <= 1000; ++i) {
            solution.isHappyHashSet(i);
            solution.isHappyTwoPointer(i);
            solution.isHappyMath(i);
        }

        // run
        long start;
        start = System.nanoTime();
        for (int i = 1; i <= TEN_MILLION; ++i)
            solution.isHappyHashSet(i);
        long timeHashSet = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 1; i <= TEN_MILLION; ++i)
            solution.isHappyTwoPointer(i);
        long timeTwoPointer = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 1; i <= TEN_MILLION; ++i)
            solution.isHappyMath(i);
        long timeMath = System.nanoTime() - start;

        System.out.println("HashSet Version Time(ms): " + timeHashSet / ONE_MILLION);
        System.out.println("Two Pointer Version Time(ms): " + timeTwoPointer / ONE_MILLION);
        System.out.println("Math Version Time(ms): " + timeMath / ONE_MILLION);
    }
}