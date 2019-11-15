package com.ywh.sorting;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/**
 * 测试基数排序
 * {@link RadixSort}
 *
 * @author ywh
 * @since 15/11/2019
 */
@DisplayName("测试基数排序")
class RadixSortTest {

    private static RadixSort solution;

    @BeforeAll
    static void init() {
        solution = new RadixSort();
    }

    @ParameterizedTest
    @CsvSource({
        "'5,2,7,9,0,6,3,-5,1,4,8,-5'",
        "'1,1,1,1,1'",
        "'9,8,-4,7,6,-1,5,4,3,2,1,0'",
        "0"
    })
    void testSort8passInBucket(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        assert nums != null;
        int[] expected = nums.clone();
        Arrays.sort(expected);
        solution.sort8passInBucket(nums);
        assertArrayEquals(expected, nums);
    }

    @ParameterizedTest
    @CsvSource({
        "'5,2,7,9,0,6,3,-5,1,4,8,-5'",
        "'1,1,1,1,1'",
        "'9,8,-4,7,6,-1,5,4,3,2,1,0'",
        "0"
    })
    void testSort4passInBucket(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        assert nums != null;
        int[] expected = nums.clone();
        Arrays.sort(expected);
        solution.sort4passInBucket(nums);
        assertArrayEquals(expected, nums);
    }

    @ParameterizedTest
    @CsvSource({
        "'5,2,7,9,0,6,3,-5,1,4,8,-5'",
        "'1,1,1,1,1'",
        "'9,8,-4,7,6,-1,5,4,3,2,1,0'",
        "0"
    })
    void testSort8passInCounting(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        assert nums != null;
        int[] expected = nums.clone();
        Arrays.sort(expected);
        solution.sort8passInCounting(nums);
        assertArrayEquals(expected, nums);
    }

    @ParameterizedTest
    @CsvSource({
        "'5,2,7,9,0,6,3,-5,1,4,8,-5'",
        "'1,1,1,1,1'",
        "'9,8,-4,7,6,-1,5,4,3,2,1,0'",
        "0"
    })
    void testSort4passInCounting(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        assert nums != null;
        int[] expected = nums.clone();
        Arrays.sort(expected);
        solution.sort4passInCounting(nums);
        assertArrayEquals(expected, nums);
    }
}
