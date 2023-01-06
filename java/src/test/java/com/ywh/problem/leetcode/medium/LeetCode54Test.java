package com.ywh.problem.leetcode.medium;

import com.ywh.util.AssertUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * 测试矩阵的螺旋遍历
 * {@link LeetCode54}
 *
 * @author ywh
 * @since 24/11/2019
 */
@DisplayName("测试矩阵的螺旋遍历")
public class LeetCode54Test {

    private static LeetCode54 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode54();
    }

    @Test
    public void testSpiralOrder() {
        AssertUtil.assertIntListEquals(Collections.emptyList(), solution.spiralOrder(new int[][] {}));
        // AssertUtil.assertIntListEquals(Collections.emptyList(),
        // solution.spiralOrder(new int[][]{null}));
        AssertUtil.assertIntListEquals(Collections.emptyList(), solution.spiralOrder(new int[][] { {} }));
        AssertUtil.assertIntListEquals(Arrays.asList(1, 2, 3), solution.spiralOrder(new int[][] { { 1, 2, 3 } }));
        AssertUtil.assertIntListEquals(Arrays.asList(1, 4, 7),
                solution.spiralOrder(new int[][] { { 1 }, { 4 }, { 7 } }));
        AssertUtil.assertIntListEquals(Arrays.asList(1, 2, 3, 6, 5, 4),
                solution.spiralOrder(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } }));
        AssertUtil.assertIntListEquals(Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5),
                solution.spiralOrder(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }));
    }

}
