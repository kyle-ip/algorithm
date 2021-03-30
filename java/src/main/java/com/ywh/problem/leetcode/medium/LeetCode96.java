package com.ywh.problem.leetcode.medium;

/**
 * 不同的二叉搜索树
 * [动态规划] [树] [数学]
 * 
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * 示例:
 *      输入: 3
 *      输出: 5
 *      解释:
 *      给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *         1         3     3      2      1
 *          \       /     /      / \      \
 *           3     2     1      1   3      2
 *          /     /       \                 \
 *         2     1         2                 3
 *
 * @author ywh
 * @since 07/11/2019
 */
public class LeetCode96 {

    /**
     * 动态规划解法
     *
     * Time: O(n^2), Space: O(n)
     *
     * @param n
     * @return
     */
    public int numTreesDP(int n) {
        // d[i] 表示 [1, i] 能构成的二叉搜索树的数量（0 个节点时，视作能构成一棵二叉搜索树）。
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; ++i) {
            // 以某个数字为根、其他数字为子树可生成的 BST 总数 = 左子树可生成 BST 个数 * 右子树可生成 BST 个数。
            // 比如 i == 3，d[3] += d[0] * d[2] + d[1] * d[1] + d[2] * d[0]，就表示 0+1+2、1+1+1、2+1+0 三种情况（左根右）。
            // 有 n 个数字，则分别以这些数字为根，求和得到二叉搜索数量之和。

            for (int j = 1; j <= i; j++) {
                 dp[i] += dp[j - 1] * dp[i - j];
            }

            // 因为左右是对称的，只需要算一半，偶数直接乘 2，奇数再额外多算一个。
//            int total = 0, mid = i / 2;
//            for (int j = 1; j <= mid; j++) {
//                total += dp[j - 1] * dp[i - j] * 2;
//            }
//            if (i % 2 == 1) {
//                total += dp[mid] * dp[i - mid - 1];
//            }
//            dp[i] = total;
        }
        return dp[n];
    }

    /**
     * 数学解法：第 n 项卡特兰数
     *
     * Time: O(n), Space: O(1)
     *
     * @param n
     * @return
     */
    public int numTreesMath(int n) {
        long ret = 1;
        for (int k = 1; k <= n; ++k) {
            ret = ret * (n + k) / k;
        }
        return (int) (ret / (n + 1));
    }
}
