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
 * 测试计数排序
 * {@link CountingSort}
 *
 * @author ywh
 * @since 14/11/2019
 */
@DisplayName("测试计数排序")
class CountingSortTest {

    private static CountingSort solution;

    @BeforeAll
    static void init() {
        solution = new CountingSort();
    }

    @ParameterizedTest
    @CsvSource({
        "'5,2,7,9,0,6,3,-5,1,4,8,-5'",
        "'1,1,1,1,1'",
        "'9,8,-4,7,6,-1,5,4,3,2,1,0'",
        "0"
    })
    void testSort(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        assert nums != null;
        int[] expected = nums.clone();
        Arrays.sort(expected);
        solution.sort(nums);
        assertArrayEquals(expected, nums);
    }

    @ParameterizedTest
    @CsvSource({
        "'5,2,7,9,0,6,3,-5,1,4,8,-5'",
        "'1,1,1,1,1'",
        "'9,8,-4,7,6,-1,5,4,3,2,1,0'",
        "0"
    })
    void testSortLeft2Right(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        assert nums != null;
        int[] expected = nums.clone();
        Arrays.sort(expected);
        solution.sortLeft2Right(nums);
        assertArrayEquals(expected, nums);
    }

    @ParameterizedTest
    @CsvSource({
        "'5,2,7,9,0,6,3,-5,1,4,8,-5'",
        "'1,1,1,1,1'",
        "'9,8,-4,7,6,-1,5,4,3,2,1,0'",
        "0"
    })
    void testSortRight2Left(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        assert nums != null;
        int[] expected = nums.clone();
        Arrays.sort(expected);
        solution.sortRight2Left(nums);
        assertArrayEquals(expected, nums);
    }

}
