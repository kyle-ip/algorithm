package com.ywh.problem.leetcode.easy;

import java.util.Arrays;

import static java.lang.Math.sqrt;

/**
 * 质数的个数
 * [数学]
 *
 * @author ywh
 * @since 17/12/2019
 */
public class LeetCode204 {

    /**
     * 埃拉托斯特尼筛法：从最小的质数开始，留下该质数、排除这个质数所有的倍数，直到 sqrt(n)
     * 
     * Time: O(n*log(log(n))), Space: O(n)
     *
     * @param n
     * @return
     */
    public int countPrimesSieve(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] primes = new boolean[n];

        // 一开始假定所有元素都是质数
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;

        for (int i = 2; i < sqrt(n); i++) {
            if (!primes[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                primes[j] = false;
            }
        }
        int cnt = 0;
        for (boolean prime : primes) {
            if (prime) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 优化版本，辅助数组用于标记合数
     *
     * @param n
     * @return
     */
    public int countPrimesSieveFast(int n) {
        // 1：选定质数筛子 i 时，从 i^2 开始即可。
        // 2：2 的倍数都不是质数，所以筛选可以从 3 开始、只关注奇数。
        // 3：如果 n 取值非常大，可以对候选集合分段筛选，减少辅助空间使用。
        if (n <= 2) {
            return 0;
        }
        int upper = (int) Math.sqrt(n);

        // 标记合数，注意此处只用来标记（便于 continue），最后不需要对数组进行统计，所以最终数组值为 false 的未必是质数。
        boolean[] composites = new boolean[n];

        // 算上 2，随后的循环只考虑奇数。
        int cnt = 1;
        for (int i = 3; i < n; i += 2) {
            // 跳过所有合数。
            if (composites[i]) {
                continue;
            }
            cnt++;
            if (i > upper) {
                continue;
            }

            // i * i 为奇数，增长步长为 2 * i，目的是跳过所有所有偶数，只标记奇数的合数。
            // 如 i = 3，则 9、15、21、27...都为合数，因此排除，在后续的循环中会被 continue。
            // j + i 不用管，本来就是偶数，会被跳过。
            for (int j = i * i; j < n; j += 2 * i) {
                composites[j] = true;
            }
        }
        return cnt;
    }
}
