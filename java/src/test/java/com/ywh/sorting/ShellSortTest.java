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
 * 测试希尔排序法
 * {@link ShellSort}
 *
 * @author ywh
 * @since 13/11/2019
 */
@DisplayName("测试希尔排序法")
class ShellSortTest {

    private static ShellSort solution;

    @BeforeAll
    static void init() {
        solution = new ShellSort();
    }

    @ParameterizedTest
    @CsvSource({
        "5,2,7,9,0,6,3,1,4,8",
        "1,1,1,1,1",
        "9,8,7,6,5,4,3,2,1,0",
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

}
