package com.ywh.problem.leetcode.medium;

/**
 * 翻转矩阵后的得分
 * [贪心]
 *
 * @author ywh
 * @since 2020/12/7/007
 */
public class LeetCode861 {
    /**
     * 先对行翻转，把每一行最左边的数都变为 1。
     * 然后对列翻转，使得除最左边的列以外的其他列中的 1 尽可能多。
     * 对于从第 2 列开始的每一列，如果该列 0 的数目多于 1 的数目，就翻转该列，其他的列则保持不变。
     *
     * Time: O(m*n), Space: O(1)
     * @param A
     * @return
     */
    public int matrixScore(int[][] A) {
        // 对于最左边的列而言，最优情况下其值都为 1，因此每个元素对分数的贡献都为 2^(n-1)，总贡献为 m*2^(n-1)。
        int m = A.length, n = A[0].length, ret = m * (1 << (n - 1));

        // 对于第 j 列而言（从第二列开始），统计 0、1 的数量，令其中的最大值为 k，则 k 是列翻转后 1 的数量，则该列总贡献为 k*2^(n-j-1)。
        // 遍历每列（从第二列开始）。
        for (int j = 1; j < n; j++) {
            int nOnes = 0;
            // 遍历每行，需要注意在统计 0、1 数量时，要考虑最初进行的行翻转。
            for (int[] row : A) {
                // 如果该行最左边元素为 1，表示没有经过行翻转，直接累计该行 1 数量；否则进行了行翻转，该元素的实际取值为 1 - A[i][j]。
                nOnes += row[0] == 1? row[j]: (1 - row[j]);
            }
            // k 取 0、1 数量中的较大者，因为无论是 0 多还是 1 多，都可以通过翻转使得该列 1 更多（行数，即列长度）。
            ret += Math.max(nOnes, m - nOnes) * (1 << (n - j - 1));
        }
        return ret;
    }
}
