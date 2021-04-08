package com.ywh.problem.leetcode.hard;

/**
 * K 个不同整数的子数组
 * [哈希表] [双指针] [滑动窗口]
 *
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定不同的子数组为好子数组。
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）返回 A 中好子数组的数目。
 * 示例 1：
 *      输入：A = [1,2,1,2,3], K = 2
 *      输出：7
 *      解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 *      输入：A = [1,2,1,3,4], K = 3
 *      输出：3
 *      解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 * 提示：
 *      1 <= A.length <= 20000
 *      1 <= A[i] <= A.length
 *      1 <= K <= A.length
 *
 * @author ywh
 * @since 12/02/2021
 */
public class LeetCode992 {

    /**
     * 恰好包含 K 个不同整数的子区间的个数
     *
     * Time: O(n), Space: O(n)
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }

    /**
     * 最多包含 K 个不同整数的子区间的个数
     *
     * @param A
     * @param K
     * @return
     */
    private int atMostKDistinct(int[] A, int K) {
        int[] counter = new int[A.length + 1];
        // [l, r) 中不同整数的个数、包含不同整数的个数小于等于 K
        int cnt = 0, ret = 0;
        for (int l = 0, r = 0; r < A.length; ) {
            // 右指针的元素首次出现，统计到 count。
            if (counter[A[r]] == 0) {
                cnt++;
            }
            counter[A[r++]]++;

            // 如果统计首次出现的元素的个数（即不同元素的个数）大于 K，则减少相应的统计值，并移动左指针。
            for (; cnt > K; l++) {
                counter[A[l]]--;
                // 左指针的元素出现次数被减到 0，count 减少。
                if (counter[A[l]] == 0) {
                    cnt--;
                }
            }
            // [l, r) 区间的长度就是对结果的贡献。
            ret += r - l;
        }
        return ret;
    }

}
