package com.ywh.problem.leetcode.hard;

import com.ywh.problem.leetcode.medium.LeetCode300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 俄罗斯套娃信封问题
 * [动态规划] [二分查找]
 * 
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 注意：不允许旋转信封。
 * 示例 1：
 *      输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 *      输出：3
 *      解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * 示例 2：
 *      输入：envelopes = [[1,1],[1,1],[1,1]]
 *      输出：1
 * 提示：
 *      1 <= envelopes.length <= 5000
 *      envelopes[i].length == 2
 *      1 <= wi, hi <= 10^4
 *
 * @author ywh
 * @since 2021/3/22
 */
public class LeetCode354 {

    /**
     *
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopesDP(int[][] envelopes) {

        // 对信封的 w 按升序、h 按降序排列。
        Arrays.sort(envelopes, (e1, e2) -> e1[0] == e2[0]? e2[1] - e1[1]: e1[0] - e2[0]);

        // 转化为最长上升子序列（的长度）问题，用动态规划求解。
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);

        // 由于排序结果中，可能存在多个相同 w 的信封被排在相邻的位置。
        // 必须要保证对于每一种 w 值最多只能选择 1 个信封。
        // 由于已对 h 按降序排列，h 值不可能组成长度超过 1 的严格递增的序列（即使 w 维度相同，h 维度也会递减，无法构成整体递增）。
        // 可忽略 w 维度，求出 h 维度的最长严格递增子序列，其长度即为答案。
        int ret = 1;
        for (int i = 0; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                // 信封 j 的 w 必然小于等于信封 i 的宽度，因此比较高度：如果高度也小于 i 则更新。
                if (envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }

    /**
     * 参考 {@link LeetCode300#lengthOfLISBinarySearch}
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopesBinarySearch(int[][] envelopes) {
        // 对信封的 w 按升序、h 按降序排列。
        Arrays.sort(envelopes, (e1, e2) -> e1[0] == e2[0]? e2[1] - e1[1]: e1[0] - e2[0]);
        // f 数组保存递增的信封的 h。
        List<Integer> f = new ArrayList<>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < envelopes.length; i++) {
            int curH = envelopes[i][1], prevH = f.get(f.size() - 1);
            // 如果递增，直接添加。
            if (curH > prevH) {
                f.add(curH);
            }
            // 如果不递增，则在 [0, f.length()) 之间找到二分查找插入的位置，设值。
            else {
                f.set(binarySearch(f, curH), curH);
            }
        }
        return f.size();
    }

    /**
     *
     * @param f
     * @param target
     * @return
     */
    public int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1, mid;
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


    /**
     * 参考 {@link LeetCode300#lengthOfLISBinarySearch}
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopesBinarySearch2(int[][] envelopes) {
        // 对信封的 w 按升序、h 按降序排列。
        Arrays.sort(envelopes, (e1, e2) -> e1[0] == e2[0]? e2[1] - e1[1]: e1[0] - e2[0]);
        int len = 0;
        int[] d = new int[envelopes.length];
        for (int[] envelope : envelopes) {
            int num = envelope[1], low = 0, high = len - 1, pos;
            // 二分查找，在数组 d 的 [low, high] 之间找到 num 应该插入的位置，其中 [low, high] 表示最长递增子数组的范围。
            while (true) {
                if (low > high) {
                    pos = low;
                    break;
                }
                int mid = low + (high - low) / 2;
                if (d[mid] == num) {
                    pos = mid;
                    break;
                } else if (num < d[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // 插入 num，如果插入的位置为当前顺序数组长度（即从数组末尾插入），表示该元素是递增顺序插入的，长度 + 1。
            d[pos] = num;
            len += pos == len ? 1 : 0;
        }
        return len;
    }
}
