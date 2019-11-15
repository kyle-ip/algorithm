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
 * 测试归并排序
 * {@link MergeSort}
 *
 * @author ywh
 * @since 14/11/2019
 */
@DisplayName("测试归并排序")
class MergeSortTest {

    private static MergeSort solution;

    @BeforeAll
    static void init() {
        solution = new MergeSort();
    }

    @ParameterizedTest
    @CsvSource({
        "'5,2,7,9,0,6,3,1,4,8'",
        "'1,1,1,1,1'",
        "'9,8,7,6,5,4,3,2,1,0'",
        "0"
    })
    void testSortIterative(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        assert nums != null;
        int[] expected = nums.clone();
        Arrays.sort(expected);
        solution.sortIterative(nums);
        assertArrayEquals(expected, nums);
    }

    @ParameterizedTest
    @CsvSource({
        "'5,2,7,9,0,6,3,1,4,8'",
        "'1,1,1,1,1'",
        "'9,8,7,6,5,4,3,2,1,0'",
        "0"
    })
    void testSortRecursive(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        assert nums != null;
        int[] expected = nums.clone();
        Arrays.sort(expected);
        solution.sortRecursive(nums);
        assertArrayEquals(expected, nums);
    }
}
