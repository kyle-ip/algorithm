package com.ywh.problem.leetcode.medium;

/**
 * 二叉搜索树的数量
 * [动态规划] [树] [数学]
 *
 * @author ywh
 * @since 07/11/2019
 */
public class LeetCode96 {

    /**
     * 动态规划
     *
     * Time: O(n^2), Space: O(n)
     *
     * @param n
     * @return
     */
    public int numTreesDP(int n) {
        if (n < 0) {
            return 0;
        }
        int[] d = new int[n + 1];

        // d[0] 表示只有一个节点时，能构成一棵二叉搜索树
        d[0] = 1;

        // d[i] 表示第 i 个递增的数字能构成的二叉搜索树的数量
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {

                // 以某个数字为根、其他数字为子树可生成的 BST 总数 = 左子树可生成 BST 个数 * 右子树可生成 BST 个数
                // 有 n 个数字，则分别以这些数字为根，求和得到二叉搜索数量之和；
                // 因为 d[2] * d[3] 和 d[3] * d[2] 分别代表作为左子树和作为右子树的情况，所以不是重复算；
                d[i] += d[j - 1] * d[i - j];
            }
        }
        return d[n];
    }

    /**
     * 第 n 项卡特兰数
     *
     * Time: O(n), Space: O(1)
     *
     * @param n
     * @return
     */
    public int numTreesMath(int n) {
        if (n < 0) {
            return 0;
        }
        long result = 1;
        for (int k = 1; k <= n; ++k) {
            result = result * (n + k) / k;
        }
        return (int) (result / (n + 1));
    }
}
