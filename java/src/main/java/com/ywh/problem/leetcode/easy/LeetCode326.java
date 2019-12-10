package com.ywh.problem.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 3 的幂
 * [数学]
 *
 * @author ywh
 * @since 2019/12/10/010
 */
public class LeetCode326 {

    /**
     * Time: O(log_3(n)), Space: O(1)
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThreeBruteForce(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }


    /**
     * 3 ^ a <= y
     * a <= log_3(y) = ln(y)/ln(3)
     * 其中 Math.log() 表示求自然对数，即 Math.log(Math.E) == 1.0
     * 当 y 取 Integer.MAX_VALUE（32 位整型可表示的最大值），以此求出 3 的幂，如果模 n == 0，则 n 也为 3 的幂
     *
     * Time: O(1), Space: O(1)
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThreeMath(int n) {
        int maxPower = (int) (Math.log(Integer.MAX_VALUE) / Math.log(3));
        int maxNum = (int) Math.pow(3, maxPower);
        return n > 0 && (maxNum % n) == 0;
    }

    /**
     * Time: O(1), Space: O(1)
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThreeHashSet(int n) {

        // 32 位整型变量可以表示的所有的 3 的幂，直接打表
        Set<Integer> set = new HashSet<>(
            Arrays.asList(
                1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441, 1594323, 4782969, 14348907, 43046721, 129140163, 387420489, 1162261467
            )
        );
        return set.contains(n);
    }
}
