package com.ywh.problem.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 无重叠区间
 * [贪心]
 *
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 *      可以认为区间的终点总是大于它的起点。
 *      区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 *      输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *      输出: 1
 *      解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *      输入: [ [1,2], [1,2], [1,2] ]
 *      输出: 2
 *      解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *      输入: [ [1,2], [2,3] ]
 *      输出: 0
 *      解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * @author ywh
 * @since 2020/12/31/031
 */
public class LeetCode435 {

    /**
     * Time: O(n*log(n)), Space: O(log(n))
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 假设在最优的选择中，[lk, rk] 是最左侧的区间，则其左侧没有区间，右侧有若干个不重叠区间。
        // 如果此时存在一个区间 [lj, rj] 使得 rj < rk，即区间 j 的右端点在区间 k 的左侧，则将区间 k 替换为区间 j。
        // 剩余的区间仍然是不重叠，因此得到另一种最优的选择方法。
        // 不断寻找右端点在首个区间右端点左侧的新区间，将首个区间替换成该区间，最终得到的首个区间就是所有可选择区间中右端点最小的区间。
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[1]));
        int n = intervals.length;
        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            if (intervals[i][0] >= right) {
                ++ans;
                right = intervals[i][1];
            }
        }
        return n - ans;
    }
}
