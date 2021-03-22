package com.ywh.problem.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 鸡蛋掉落
 * [数学] [二分搜索] [动态规划]
 *
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * 示例 1：
 *      输入：K = 1, N = 2
 *      输出：2
 *      解释：
 *      鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 *      否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 *      如果它没碎，那么我们肯定知道 F = 2 。
 *      因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 * 示例 2：
 *      输入：K = 2, N = 6
 *      输出：3
 * 示例 3：
 *      输入：K = 3, N = 14
 *      输出：4
 * 提示：
 *      1 <= K <= 100
 *      1 <= N <= 10000
 *
 * @author ywh
 * @since 2020/12/11/011
 */
public class LeetCode887 {

    /**
     *
     *
     * @param K
     * @param N
     * @param memo
     * @return
     */
    private int superEggDrop(int K, int N, Map<Integer, Integer> memo) {
        // 以鸡蛋数、楼层数 (K, N) 表示状态，当从 X 楼扔下鸡蛋时：
        //      如果鸡蛋不碎，状态变成 (K, N-X)，即鸡蛋数不变，答案只可能在上方的 N−X 层楼，因此把原问题缩小为规模 (K,N−X) 的子问题；
        //      如果鸡蛋碎，那么状态变成 (K-1, X-1)，即损失一个鸡蛋，但知道答案只可能在第 X 楼下方的 X-1 层楼中。因此把原问题缩小为规模 (K−1,X−1) 的子问题。
        // 设状态方程 dp(K, N) 为状态 (K, N) 下解决问题所需的最少步数：
        //                                     下方        上方
        //      dp(K, N) = 1 + min(max(dp(K-1, X-1), dp(K, N-X)))
        //                   1<=X<=N
        // 要保证最坏情况下（无论 F 的值如何），dp(K, N) 的值最小，
        // 必须保证“鸡蛋碎了之后接下来需要的步数”和“鸡蛋没碎之后接下来需要的步数”二者的最大值最小。
        if (memo.containsKey(N * 100 + K)) {
            return memo.get(N * 100 + K);
        }
        int ret;
        if (N == 0) {
            ret = 0;
        } else if (K == 1) {
            ret = N;
        } else {
            // 在所有满足条件的 XX 上进行二分搜索。对于状态 (K, N) 而言，X 即为 [1, N] 中的任一整数。
            int low = 1, high = N;
            while (low + 1 < high) {
                int x = (low + high) / 2;
                // 从楼下找、在楼上找。
                int f1 = superEggDrop(K - 1, x - 1, memo), f2 = superEggDrop(K, N - x, memo);
                if (f1 < f2) {
                    low = x;
                } else if (f1 > f2) {
                    high = x;
                } else {
                    low = high = x;
                }
            }
            ret = 1 + Math.min(
                Math.max(superEggDrop(K - 1, low - 1, memo), superEggDrop(K, N - low, memo)),
                Math.max(superEggDrop(K - 1, high - 1, memo), superEggDrop(K, N - high, memo))
            );
        }
        // 以 N * 100 + K 作为 Key，避免哈希冲突。
        memo.put(N * 100 + K, ret);
        return ret;
    }

    /**
     * 动态规划解法：目的是求出鸡蛋会被摔碎的临界高度。
     *
     * Time: O(K*N*log(N)), Space: O(K*N)
     *
     * @param K
     * @param N
     * @return
     */
    public int superEggDrop(int K, int N) {
        return superEggDrop(K, N, new HashMap<>());
    }

    /**
     * 决策单调性解法
     *
     * @param K
     * @param N
     * @return
     */
    public int superEggDrop2(int K, int N) {
        // Right now, dp[i] represents dp(1, i)
        int[] dp = new int[N+1];
        for (int i = 0; i <= N; ++i) {
            dp[i] = i;
        }
        for (int k = 2; k <= K; ++k) {
            // Now, we will develop dp2[i] = dp(k, i)
            int[] dp2 = new int[N + 1];
            int x = 1;
            for (int n = 1; n <= N; ++n) {
                // Let's find dp2[n] = dp(k, n)
                // Increase our optimal x while we can make our answer better.
                // Notice max(dp[x-1], dp2[n-x]) > max(dp[x], dp2[n-x-1])
                // is simply max(T1(x-1), T2(x-1)) > max(T1(x), T2(x)).
                while (x < n && Math.max(dp[x - 1], dp2[n - x]) > Math.max(dp[x], dp2[n - x - 1])) {
                    x++;
                }
                // The final answer happens at this x.
                dp2[n] = 1 + Math.max(dp[x - 1], dp2[n - x]);
            }
            dp = dp2;
        }
        return dp[N];
    }

    /**
     * 逆向解法
     *
     * Time: O(K*N^(1/K)), Space: O(K*N)
     *
     * @param K
     * @param N
     * @return
     */
    public int superEggDropMath(int K, int N) {
        // 逆向思维，如果可以做 T 次操作，有 K 个鸡蛋，可以找到最高的 N 是多少？
        // 设 N = f(T, K)，如果找到所有的 f(T, K)，只需要找出最小的满足 f(T, K) >= N 的 T。
        // 假设扔出一个鸡蛋：
        //      如果鸡蛋不碎，对应 f(T-1, K)，即该层的上方可以有 f(T-1, K) 层。
        //      如果鸡蛋碎，对应 f(T-1, K-1)，即该层的下方可以有 f(T-1, K-1) 层。
        // 可得状态转移方程：
        //      f(T, K) = 1 + f(T-1, K-1) + f(T-1, K)
        // 边界条件：当 T >= 1 时 f(T, 1) = T，当 K >= 1 时 f(1, K) = 1。
        // 由于操作次数不会超过楼层数，因此 T <= N，著需要算出 f(N, K) 内所有的 f 值即可。
        int ret = 1;
        int[][] f = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                f[i][j] = 1 + f[i - 1][j - 1] + f[i - 1][j];
            }
            if (f[i][K] >= N) {
                ret = i;
                break;
            }
        }
        return ret;
    }
}
